package ru.hloffice.plugins.jira.setup;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.atlassian.jira.web.action.JiraWebActionSupport;
import ru.hloffice.plugins.jira.api.MyPluginComponent;

import javax.inject.Inject;

public class DadataSetupAction extends JiraWebActionSupport
{
    private static final Logger log = LoggerFactory.getLogger(DadataSetupAction.class);

    private final MyPluginComponent myPluginComponent;

    private String cdnkey;
    private String cdnapi;
    private String cdncss;

    @Inject
    public DadataSetupAction(MyPluginComponent myPluginComponent) {
        this.myPluginComponent = myPluginComponent;
    }

    @Override
    public String execute() throws Exception {

        return super.execute(); //returns SUCCESS
//        return SUCCESS;
    }

    @Override
    public String doDefault() throws Exception {

        this.cdnapi = " ";
        this.cdncss = " ";
        this.cdnkey = " ";

        String cfg = myPluginComponent.getConfigJson();

        if ((cfg == null) || (cfg.isEmpty())) {
//            log.warn(" === cfg is empty === ");
            return SUCCESS;
        }

//        if (cfg.isEmpty()) {
//            return SUCCESS;
//        }

        JsonParser parser = new JsonParser();
        JsonObject cfgObj = parser.parse(cfg).getAsJsonObject();

        String currVar;

        currVar = cfgObj.get("cdnapi").getAsString();
        if (currVar != null) {
            this.cdnapi = currVar;
        }

        currVar = cfgObj.get("cdncss").getAsString();
        if (currVar != null) {
            this.cdncss = currVar;
        }

        currVar = cfgObj.get("cdnkey").getAsString();
        if (currVar != null) {
            this.cdnkey = currVar;
        }

        return SUCCESS;
    }


    public String doSave() throws Exception {

//        log.warn(" in save action ");

        String paramServerName;

        JsonObject params = new JsonObject();

        paramServerName = request.getParameter("cdnapi");
        if (paramServerName != null) {
            params.addProperty("cdnapi", paramServerName);
            this.cdnapi = paramServerName;
        } else {
            params.addProperty("cdnapi", "");
            this.cdnapi = "";

        }



        paramServerName = request.getParameter("cdncss");
        if (paramServerName != null) {
            params.addProperty("cdncss", paramServerName);
            this.cdncss = paramServerName;
        } else {
            params.addProperty("cdncss", "");
            this.cdncss = "";
        }



        paramServerName = request.getParameter("cdnkey");
        if (paramServerName != null) {
            params.addProperty("cdnkey", paramServerName);
            this.cdnkey = paramServerName;
//            log.warn(" cdnkey from request ");
//            log.warn(paramServerName);
        } else {
            params.addProperty("cdnkey", "");
            this.cdnkey = "";
        }


        myPluginComponent.setConfigJson(params.toString());


        return SUCCESS;

    }


    public String getCdnkey() {
        return cdnkey;
    }

    public void setCdnkey(String cdnkey) {
        this.cdnkey = cdnkey;
    }

    public String getCdnapi() {
        return cdnapi;
    }

    public void setCdnapi(String cdnapi) {
        this.cdnapi = cdnapi;
    }

    public String getCdncss() {
        return cdncss;
    }

    public void setCdncss(String cdncss) {
        this.cdncss = cdncss;
    }
}
