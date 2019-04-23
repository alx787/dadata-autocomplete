package ru.hloffice.plugins.jira.impl;



import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
//import com.atlassian.sal.api.ApplicationProperties;
import com.atlassian.sal.api.pluginsettings.PluginSettings;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import ru.hloffice.plugins.jira.api.MyPluginComponent;

import javax.inject.Inject;
import javax.inject.Named;

@ExportAsService ({MyPluginComponent.class})
@Named ("pluginSettingsDadadta")
public class MyPluginComponentImpl implements MyPluginComponent
{
//    @ComponentImport
//    private final ApplicationProperties applicationProperties;

    private final PluginSettings pluginSettings;
    private static final String PLUGIN_STORAGE_KEY = "ru.hloffice.";
    private static final String CONFIG_KEY = "dadataplugin";


    @Inject
    public MyPluginComponentImpl(@ComponentImport PluginSettingsFactory pluginSettingsFactory)
    {
        this.pluginSettings = pluginSettingsFactory.createGlobalSettings();
    }

    private String getSettingValue(String settingKey) {
        if (settingKey == null) {
            return "";
        } else {
            return (String) pluginSettings.get(PLUGIN_STORAGE_KEY + settingKey);
        }
    }

    private void setSettingValue(String settingKey, String settingValue) {
        if (settingKey == null)
            return;

        if (settingValue == null) {
            this.pluginSettings.put(PLUGIN_STORAGE_KEY + settingKey,"");
        } else {
            this.pluginSettings.put(PLUGIN_STORAGE_KEY + settingKey, settingValue);
        }
    }



    @Override
    public String getConfigJson() {
        return getSettingValue(CONFIG_KEY);
    }

    @Override
    public void setConfigJson(String json) {
        setSettingValue(CONFIG_KEY, json);
    }

//    public String getName()
//    {
//        if(null != applicationProperties)
//        {
//            return "myComponent:" + applicationProperties.getDisplayName();
//        }
//
//        return "myComponent";
//    }
}