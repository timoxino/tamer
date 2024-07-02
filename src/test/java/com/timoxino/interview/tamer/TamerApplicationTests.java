package com.timoxino.interview.tamer;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.timoxino.interview.tamer.service.OpenAiService;

@SpringBootTest
@Disabled
class TamerApplicationTests {

	static final String CV = """
				Libardo Andres Samboni Rubio
			Cellphone: 315 853 81 69 – 318 338 6278
			E-mail: landsamboni@gmail.com
			Address: Calle 49 #56-65 Armenia, Quindio (Colombia).
			Profile
			I am an experienced bilingual telecommunications engineer with an MBA, offering over 12+ years of expertise in IT project management and a focus on cloud engineering. As an AWS Solutions Architect, AWS Security Specialty, and AWS SysOps Administrator certified professional, I possess extensive knowledge in deploying and managing secure and scalable cloud solutions. With a strong background in IT infrastructure, networking, and information security, both on-premises and in the cloud, I have successfully implemented cloud computing strategies and optimized performance. I also hold certifications in ITIL v3, COBIT 5, and SCRUM methodologies, enabling me to effectively lead cross- functional teams and deliver IT services seamlessly.
			Skills
			• AWS Cloud Implementation.
			• Project Management.
			• Security architecture and technologies.
			SCRUM certified.
			Master in Business Administration (MBA), Universidad Internacional de la Rioja – 2022
			§ AWS Certified Cloud Practitioner.
			§ Certified SCRUM Master SMPC, ISACA - 2018.
			§ Certified COBIT 5 Foundation Examination, ISACA - 2018.
			§ Certified ITIL Foundation in IT Service Management, EXIN - 2015. § Control and Information Security, SENA - 2013.
			§ Virtualization Infrastructure with VMWARE, CETEC - 2012.
			§ CISCO CCNA Exploration 4 – Accessing the WAN, SENA - 2012.
			Work Experience
			NUB8 LLC
				""";

	@Autowired
	OpenAiService openAiService;

	@Test
	void contextLoads() {
		List<String> detectedSkills = openAiService.detectSkills(CV, "Project Manager");
		Integer evaluatedSeniorityLevel = openAiService.evaluateSeniorityLevel(CV, "Project Manager");
		assertTrue(detectedSkills.size() >= 8);
		assertTrue(evaluatedSeniorityLevel >= 3);
	}

}
