package com.uitgis.jms.def;

import lombok.Getter;
import lombok.Setter;

public enum Profile {
	MYSQL("mysql"), POSTGRE("postgre");

	@Getter
	@Setter
	private String profileName;

	Profile(String profileName) {
		this.profileName = profileName;
	}

	public static Profile getProfileByName(String profileName) {
		for (Profile profile : Profile.values()) {
			if (profile.profileName.equals(profileName)) {
				return profile;
			}
		}
		return MYSQL;
	}
}
