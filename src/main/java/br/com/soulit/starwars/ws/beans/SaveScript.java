package br.com.soulit.starwars.ws.beans;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.soulit.starwars.bo.beans.IScriptService;
import br.com.soulit.starwars.commons.DuplicateResultException;
import br.com.soulit.starwars.db.entity.ScriptDB;
import br.com.soulit.starwars.ws.response.Status;

@Service
public class SaveScript extends AbstractRestService<String, Status> implements ISaveScript {

    private static final long serialVersionUID = 1870339167449420056L;

    @Autowired
    private IScriptService    scriptService;
                              
    @Override
    protected Status process(String in) throws Exception {
        if (StringUtils.isEmpty(in)) {
            throw new IllegalArgumentException();
        }
        final ScriptDB script = scriptService.find(in);
        if (script != null) {
            throw new DuplicateResultException("Movie script already received");
        }
        scriptService.save(in);
        scriptService.parse(in);
        return handleSuccess();
    }

    private Status handleSuccess() {
        final Status st = new Status();
        st.setMessage("Movie script successfully received");
        return st;
    }
}
