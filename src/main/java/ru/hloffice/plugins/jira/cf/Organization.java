package ru.hloffice.plugins.jira.cf;

import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.customfields.impl.GenericTextCFType;
import com.atlassian.jira.issue.customfields.manager.GenericConfigManager;
import com.atlassian.jira.issue.customfields.persistence.CustomFieldValuePersister;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.TextFieldCharacterLengthValidator;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutItem;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.JiraImport;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ru.hloffice.plugins.jira.api.MyPluginComponent;

import javax.annotation.Nonnull;
import java.util.Map;

@Scanned
public class Organization extends GenericTextCFType
{

    private String cdnkey;
    private String cdnapi;
    private String cdncss;


    private final MyPluginComponent myPluginComponent;

    public Organization(@JiraImport CustomFieldValuePersister customFieldValuePersister,
                        @JiraImport GenericConfigManager genericConfigManager,
                        @JiraImport TextFieldCharacterLengthValidator textFieldCharacterLengthValidator,
                        @JiraImport JiraAuthenticationContext jiraAuthenticationContext,
                        MyPluginComponent myPluginComponent)
    {
        super(customFieldValuePersister, genericConfigManager, textFieldCharacterLengthValidator, jiraAuthenticationContext);
        this.myPluginComponent = myPluginComponent;
    }

    @Nonnull
    @Override
    public Map<String, Object> getVelocityParameters(Issue issue, CustomField field, FieldLayoutItem fieldLayoutItem) {

        final Map<String, Object> map = super.getVelocityParameters(issue, field, fieldLayoutItem);

        // This method is also called to get the default value, in
        // which case issue is null so we can't use it to add currencyLocale
        if (issue == null) {
            return map;
        }



        // восстановим параметры из поля
        String cfg = myPluginComponent.getConfigJson();

        if ((cfg == null) || (cfg.isEmpty())) {
            cdnkey = " ";
            cdnapi = " ";
            cdncss = " ";
        }

        JsonParser parser = new JsonParser();
        JsonObject cfgObj = parser.parse(cfg).getAsJsonObject();

        String currVar;

        currVar = cfgObj.get("cdnapi").getAsString();
        if (currVar != null) {
            cdnapi = currVar;
        }

        currVar = cfgObj.get("cdncss").getAsString();
        if (currVar != null) {
            cdncss = currVar;
        }

        currVar = cfgObj.get("cdnkey").getAsString();
        if (currVar != null) {
            cdnkey = currVar;
        }


        map.put("cdnkey", cdnkey);
        map.put("cdnapi", cdnapi);
        map.put("cdncss", cdncss);

        return map;
    }


}

