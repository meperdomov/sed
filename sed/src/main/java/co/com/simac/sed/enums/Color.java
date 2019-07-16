package co.com.simac.sed.enums;

public enum Color {

	YELLOW("YELLOW"), RED("RED"), GREEN("GREEN");

	private String description;

	private Color(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public static Color getColorByDescription(String description) {
		for (Color color : Color.values()) {
			if (color.getDescription().equals(description)) {
				return color;
			}
		}
		String message = String.format("Color %s no encontrado, por favor verifique", description);
		throw new IllegalArgumentException(message);
	}

}
