package ru.hloffice.plugins.jira.searchers;

import com.atlassian.jira.issue.customfields.searchers.TextSearcher;
//import com.atlassian.jira.issue.customfields.searchers.ExactTextSearcher;
import com.atlassian.jira.issue.customfields.searchers.transformer.CustomFieldInputHelper;
import com.atlassian.jira.jql.operand.JqlOperandResolver;
import com.atlassian.jira.web.FieldVisibilityManager;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.JiraImport;

@Scanned
public class DaDataCFTextSearcher extends TextSearcher {
    public DaDataCFTextSearcher(@JiraImport FieldVisibilityManager fieldVisibilityManager,
                                @JiraImport JqlOperandResolver jqlOperandResolver,
                                @JiraImport CustomFieldInputHelper customFieldInputHelper) {
        super(fieldVisibilityManager, jqlOperandResolver, customFieldInputHelper);
    }
}

//@Scanned
//public class DaDataCFTextSearcher extends ExactTextSearcher {
//
//    public DaDataCFTextSearcher(@JiraImport JqlOperandResolver jqlOperandResolver,
//                                @JiraImport CustomFieldInputHelper customFieldInputHelper,
//                                @JiraImport FieldVisibilityManager fieldVisibilityManager) {
//        super(jqlOperandResolver, customFieldInputHelper, fieldVisibilityManager);
//    }
//}