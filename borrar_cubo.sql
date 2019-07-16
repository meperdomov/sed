DELETE FROM sed.dbo.consumo_actividad
DELETE FROM sed.dbo.actividad 
DELETE FROM sed.dbo.consumo_medidor

DELETE FROM sed.dbo.medidor
DELETE FROM sed.dbo.equipamiento

DELETE FROM sed.dbo.orden_produccion
DELETE FROM sed.dbo.ruta
DELETE FROM sed.dbo.material

DELETE FROM sed.dbo.tipo_paro
DELETE FROM sed.dbo.categoria_paro
DELETE FROM sed.dbo.categoria_oee

UPDATE siop.dbo.mensaje_integracion SET procesadoOlap = 0