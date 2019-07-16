package co.com.simac.sed.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.apache.commons.codec.binary.Base64;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.SerializerProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import co.com.simac.sed.mvc.dto.DTOGenerico;
import co.com.simac.sed.mvc.modelo.EntidadGenerica;

public class Utilities {

	@SuppressWarnings("unchecked")
	public static <T extends DTOGenerico> List<T> toDTOList(List<? extends EntidadGenerica> list, Class<T> type,
			String... camposIgnorados) {
		List<T> result = new ArrayList<T>();
		for (EntidadGenerica entity : list) {
			
			DTOGenerico dto = entity.getDTO(camposIgnorados);
			if (type.isInstance(dto)) {
				result.add((T) dto);
			} else {
				try {
					throw new Exception("El mÃ©todo getDTO() de la entidad " + entity.getClass().getName()
							+ " no devuelve un DTO de la clase " + type.getName());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public static <T extends DTOGenerico> PageDTO<T> toPageDTO(Page<? extends EntidadGenerica> pageEntity,
			Class<T> type, String... camposIgnorados) {
		PageDTO<T> pageDTO = new PageDTO<>();
		pageDTO.setSize(pageEntity.getSize());
		pageDTO.setPage(pageEntity.getNumber());
		pageDTO.setTotalItems(pageEntity.getTotalElements());
		pageDTO.setTotalPages(pageEntity.getTotalPages());
		pageDTO.setResults(toDTOList(pageEntity.getContent(), type, camposIgnorados));
		return pageDTO;
	}

	public static void toEntity(EntidadGenerica entidad, DTOGenerico dto) {
		try {
			BeanUtils.copyProperties(dto, entidad);
			Field[] fields = entidad.getClass().getDeclaredFields();
			for (Field field : fields) {
				ManyToOne manyToOne = (ManyToOne) field.getAnnotation(ManyToOne.class);
				OneToOne oneToOne = (OneToOne) field.getAnnotation(OneToOne.class);

				if (manyToOne != null || oneToOne != null) {

					String fieldName = field.getName();
					String fieldNameCapitalize = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
					String getDtoMethodName = "get" + fieldNameCapitalize + "DTO";

					Method getDtoMethod = dto.getClass().getMethod(getDtoMethodName);
					DTOGenerico dtoGenerico = (DTOGenerico) getDtoMethod.invoke(dto);

					if (dtoGenerico != null) {
						EntidadGenerica entidadGenerica = (EntidadGenerica) Class.forName(field.getType().getName())
								.newInstance();
						BeanUtils.copyProperties(dtoGenerico, entidadGenerica);
						String setMethodName = "set" + fieldNameCapitalize;
						Method setMethod = entidad.getClass().getMethod(setMethodName, new Class[] { field.getType() });
						setMethod.invoke(entidad, new Object[] { entidadGenerica });
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		String formattedDate = dateFormat.format(date);
		gen.writeString(formattedDate);
	}

	public static String Encrypt(String texto) {

		String secretKey = "qualityinfosolutions"; // llave para encriptar datos
		String base64EncryptedString = "";

		try {

			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
			byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

			SecretKey key = new SecretKeySpec(keyBytes, "DESede");
			Cipher cipher = Cipher.getInstance("DESede");
			cipher.init(Cipher.ENCRYPT_MODE, key);

			byte[] plainTextBytes = texto.getBytes("utf-8");
			byte[] buf = cipher.doFinal(plainTextBytes);
			byte[] base64Bytes = Base64.encodeBase64(buf);
			base64EncryptedString = new String(base64Bytes);

		} catch (Exception ex) {
		}
		return base64EncryptedString;
	}

	public static String Decrypt(String textoEncriptado) throws Exception {

		String secretKey = "qualityinfosolutions"; // llave para encriptar datos
		String base64EncryptedString = "";

		try {
			byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
			byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
			SecretKey key = new SecretKeySpec(keyBytes, "DESede");

			Cipher decipher = Cipher.getInstance("DESede");
			decipher.init(Cipher.DECRYPT_MODE, key);

			byte[] plainText = decipher.doFinal(message);

			base64EncryptedString = new String(plainText, "UTF-8");

		} catch (Exception ex) {
		}
		return base64EncryptedString;
	}

}
