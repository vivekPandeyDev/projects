package com.example.soapfileservice;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "createDocumentResponse", namespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
public class CreateDocumentResponse {
	private String objectId;

	@XmlElement(name = "objectId", namespace = "http://docs.oasis-open.org/ns/cmis/messaging/200908/")
	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
}
