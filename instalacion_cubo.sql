USE [sed]
GO
/****** Object:  StoredProcedure [dbo].[OLAP_CerrarActividad]    Script Date: 14/06/2017 18:10:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
INSERT INTO oauth_client_details (client_id, access_token_validity, additional_information, authorities, authorized_grant_types, autoapprove, client_secret, refresh_token_validity, resource_ids, scope, web_server_redirect_uri) VALUES ('sed', NULL, '{}', 'PERM_READ_FOO', 'password,refresh_token', '', 'secret', NULL, '', 'read,write', '');
GO
GO
CREATE PROCEDURE [dbo].[OLAP_CerrarActividad]
AS
BEGIN
	DECLARE @id1 int
	DECLARE @nombrePaso1 varchar(255)
	DECLARE @fechaIngreso1 datetime2(3)
	DECLARE @paso_siguiente_id1 int
	DECLARE @paso_anterior_id1 int
	DECLARE @ejecucionRB_id1 int
	DECLARE actividades1 CURSOR FOR SELECT id, nombre_paso, fecha_ingreso, paso_siguiente_id, paso_anterior_id, ejecucion_rb_id FROM sed.dbo.actividad WHERE fecha_salida IS NULL ORDER BY fecha_ingreso ASC

	OPEN actividades1
	FETCH NEXT FROM actividades1 INTO @id1, @nombrePaso1, @fechaIngreso1, @paso_siguiente_id1, @paso_anterior_id1, @ejecucionRB_id1
	WHILE @@fetch_status = 0
	BEGIN
		DECLARE @id2 int 
		DECLARE @nombrePaso2 varchar(255)
		DECLARE @fechaIngreso2 datetime2(3)
		DECLARE @paso_siguiente_id2 int
		DECLARE @paso_anterior_id2 int
		DECLARE actividades2 CURSOR FOR SELECT id, nombre_paso, fecha_ingreso, paso_siguiente_id, paso_anterior_id FROM sed.dbo.actividad WHERE paso_anterior_id = @paso_siguiente_id1 AND ejecucion_rb_id = @ejecucionRB_id1 AND fecha_ingreso >= @fechaIngreso1 ORDER BY fecha_ingreso ASC
		
		/*PRINT CONVERT(varchar(255),@id1) +' '+ CONVERT(varchar(255),@paso_id1)+' '+ @nombrePaso1*/
		OPEN actividades2
		FETCH NEXT FROM actividades2 INTO @id2, @nombrePaso2, @fechaIngreso2, @paso_siguiente_id2, @paso_anterior_id2 
		WHILE @@FETCH_STATUS = 0
			BEGIN
				/*PRINT '---------'+CONVERT(varchar(255),@id2)+' '+@nombrePaso2*/
				DECLARE @duracion float 
				SELECT @duracion = DATEDIFF(SECOND,@fechaIngreso1,@fechaIngreso2)/60
				UPDATE sed.dbo.actividad SET fecha_salida = @fechaIngreso2, duracion = @duracion  WHERE id = @id1
				BREAK
				FETCH NEXT FROM actividades2 INTO @id2, @nombrePaso2, @fechaIngreso2, @paso_siguiente_id2, @paso_anterior_id2
			END
		CLOSE actividades2
		DEALLOCATE actividades2
		FETCH NEXT FROM actividades1 INTO @id1, @nombrePaso1, @fechaIngreso1, @paso_siguiente_id1, @paso_anterior_id1, @ejecucionRB_id1
	END
	CLOSE actividades1
	DEALLOCATE actividades1
END

GO
/****** Object:  StoredProcedure [dbo].[OLAP_Interpolacion]    Script Date: 14/06/2017 18:10:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [dbo].[OLAP_Interpolacion] 
AS
BEGIN
	DECLARE @actividad_id int
	DECLARE @equipo_id int
	DECLARE @fechaIngreso datetime2(7)
	DECLARE @fechaSalida datetime2(7)

	/** Actividades **/

	DECLARE actividades CURSOR FOR SELECT a.id, a.fecha_ingreso, a.fecha_salida, a.equipo_id FROM sed.dbo.actividad AS a
	INNER JOIN equipamiento AS e ON a.equipo_id = e.id  WHERE a.fecha_salida IS NOT NULL AND e.consume_por_actividad = 1 ORDER BY fecha_ingreso ASC
	OPEN actividades
	FETCH NEXT FROM actividades INTO @actividad_id, @fechaIngreso, @fechaSalida, @equipo_id
	WHILE @@fetch_status = 0
	BEGIN

		DECLARE @cantidadFilas int

		SET @cantidadFilas = (SELECT COUNT(me.id) FROM equipamiento AS e
			INNER JOIN medidor AS me ON me.equipo_id = e.id
			WHERE e.id = @equipo_id)

		IF @cantidadFilas > 0
			BEGIN
			/** Medidores **/
		    DECLARE medidores CURSOR FOR SELECT me.id FROM equipamiento AS e
			INNER JOIN medidor AS me ON me.equipo_id = e.id
			WHERE e.id = @equipo_id
			END
		ELSE	
			/** Medidores de motores **/	
			DECLARE medidores CURSOR FOR SELECT me.id FROM sed.dbo.equipamiento AS p
			INNER JOIN sed.dbo.equipamiento AS h ON h.padre_id = p.id
			INNER JOIN sed.dbo.medidor AS me ON me.equipo_id = h.id
			WHERE p.id = @equipo_id

		DECLARE @medidor_id int

		DECLARE @consumoActividad float
		SET @consumoActividad = 0

		OPEN medidores
		FETCH NEXT FROM medidores INTO @medidor_id
		WHILE @@fetch_status = 0
		BEGIN

			/** Consumos **/ 
			DECLARE consumos CURSOR FOR	SELECT co.fecha_inicio, co.fecha_fin, co.consumo FROM consumo_medidor AS co
			WHERE co.medidor_id = @medidor_id AND @fechaIngreso < co.fecha_fin AND @fechaSalida > co.fecha_inicio
			ORDER BY fecha_inicio

			DECLARE @fechaInicio datetime2(7)
			DECLARE @fechaFin datetime2(7)
			DECLARE @consumo float
			DECLARE @consumoMedidorActividad float 
			SET @consumoMedidorActividad = 0

			OPEN consumos
			FETCH NEXT FROM consumos INTO @fechaInicio, @fechaFin, @consumo
			WHILE @@fetch_status = 0
			BEGIN

				DECLARE @duracionIntervaloConsumo int
				SET @duracionIntervaloConsumo = DATEDIFF(MINUTE, @fechaInicio, @fechaFin)

				DECLARE @consumoPorMinutoIntervalo float
				SET @consumoPorMinutoIntervalo = @consumo/@duracionIntervaloConsumo

				DECLARE @duracionActividadIntervalo float 

				DECLARE @consumoIntervalo float

							IF @fechaIngreso < @fechaFin  AND @fechaIngreso > @fechaInicio
				IF @fechaSalida < @fechaFin  AND @fechaSalida > @fechaInicio
					SET @duracionActividadIntervalo = DATEDIFF(MINUTE, @fechaIngreso, @fechaSalida)
				ELSE
					SET @duracionActividadIntervalo = DATEDIFF(MINUTE, @fechaIngreso, @fechaFin)
				ELSE IF @fechaSalida < @fechaFin  AND @fechaSalida > @fechaInicio
					SET @duracionActividadIntervalo = DATEDIFF(MINUTE, @fechaInicio, @fechaSalida)
				ELSE 
					SET @duracionActividadIntervalo = DATEDIFF(MINUTE, @fechaInicio, @fechaFin) 					
			
				SET @consumoIntervalo = @duracionActividadIntervalo * @consumoPorMinutoIntervalo

				SET @consumoMedidorActividad = @consumoMedidorActividad + @consumoIntervalo


			FETCH NEXT FROM consumos INTO @fechaInicio, @fechaFin, @consumo
			END
			CLOSE consumos
			DEALLOCATE consumos

			SET @consumoActividad = @consumoActividad + @consumoMedidorActividad

		FETCH NEXT FROM medidores INTO @medidor_id
		END
		CLOSE medidores
		DEALLOCATE medidores


		IF NOT EXISTS (SELECT * FROM dbo.consumo_actividad WHERE actividad_id = @actividad_id)
		BEGIN
		   INSERT INTO dbo.consumo_actividad (consumo, actividad_id, unidad_medida_id, variable_id) 
			VALUES(@consumoActividad, @actividad_id , 1, 1) 
	    END
	FETCH NEXT FROM actividades INTO @actividad_id, @fechaIngreso, @fechaSalida, @equipo_id
	END
	CLOSE actividades
	DEALLOCATE actividades
END


GO
/****** Object:  View [dbo].[empresa]    Script Date: 14/06/2017 18:10:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [dbo].[empresa] AS 
SELECT e.* FROM sed.dbo.equipamiento AS e
WHERE e.padre_id IS NULL


GO
/****** Object:  View [dbo].[sitio]    Script Date: 14/06/2017 18:10:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [dbo].[sitio] AS 
SELECT e.* FROM sed.dbo.equipamiento AS e
INNER JOIN empresa AS emp ON e.padre_id=emp.id


GO
/****** Object:  View [dbo].[planta]    Script Date: 14/06/2017 18:10:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [dbo].[planta] AS 
SELECT e.* FROM sed.dbo.equipamiento AS e
INNER JOIN sitio AS sit ON e.padre_id=sit.id

GO
/****** Object:  View [dbo].[area]    Script Date: 14/06/2017 18:10:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE VIEW [dbo].[area] AS 
SELECT e.* FROM sed.dbo.equipamiento AS e
INNER JOIN planta AS plant ON e.padre_id = plant.id

GO
