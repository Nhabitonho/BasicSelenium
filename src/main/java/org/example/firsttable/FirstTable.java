package org.example.firsttable;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FirstTable {
    private String automationTool;
    private String type;
    private String link;

    public String getAutomationTool() {
        return automationTool;
    }

    public void setAutomationTool(String automationTool) {
        this.automationTool = automationTool;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(WebElement link) {
        try {
            String subTab = link.findElement(By.tagName("a")).getAttribute("href");
            this.link = subTab;
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
