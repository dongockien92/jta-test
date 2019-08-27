package com.uitgis.jms.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.uitgis.jms.def.Profile;

@Component
public class PropsComponent {
	@Value("${spring.profiles.active}")
	private String activeProfile;

	public Profile getActiveProfile() {
		return Profile.getProfileByName(activeProfile);
	}

}
