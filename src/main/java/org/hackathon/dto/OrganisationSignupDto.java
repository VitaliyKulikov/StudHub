package org.hackathon.dto;

import javax.validation.constraints.NotBlank;

public class OrganisationSignupDto extends SignupDto {

    @NotBlank
    protected String orgName;

    private String description;

    private String areaOfExpertise;

    private String webpageReference;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAreaOfExpertise() {
        return areaOfExpertise;
    }

    public void setAreaOfExpertise(String areaOfExpertise) {
        this.areaOfExpertise = areaOfExpertise;
    }

    public String getWebpageReference() {
        return webpageReference;
    }

    public void setWebpageReference(String webpageReference) {
        this.webpageReference = webpageReference;
    }
}
