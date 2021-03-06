package ru.hloffice.plugins.jira.cf;

import com.atlassian.jira.issue.customfields.impl.GenericTextCFType;
import com.atlassian.jira.issue.customfields.manager.GenericConfigManager;
import com.atlassian.jira.issue.customfields.persistence.CustomFieldValuePersister;
import com.atlassian.jira.issue.fields.TextFieldCharacterLengthValidator;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.JiraImport;

@Scanned
public class ZipCode extends GenericTextCFType
{
    public ZipCode(@JiraImport CustomFieldValuePersister customFieldValuePersister, @JiraImport GenericConfigManager genericConfigManager, @JiraImport TextFieldCharacterLengthValidator textFieldCharacterLengthValidator, @JiraImport JiraAuthenticationContext jiraAuthenticationContext)
    {
        super(customFieldValuePersister, genericConfigManager, textFieldCharacterLengthValidator, jiraAuthenticationContext);
    }
}
