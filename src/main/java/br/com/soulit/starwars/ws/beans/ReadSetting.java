package br.com.soulit.starwars.ws.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.soulit.starwars.bo.beans.ISceneSettingService;
import br.com.soulit.starwars.commons.ResultNotFoundException;
import br.com.soulit.starwars.ws.response.SceneSettings;

@Service
public class ReadSetting extends AbstractRestService<Long, SceneSettings> implements IReadSetting {

    private static final long    serialVersionUID = 4448619240831165822L;
                                                  
    @Autowired
    private ISceneSettingService sceneSettingService;
                                 
    @Override
    protected SceneSettings process(Long in) throws Exception {
        if (in == null) {
            throw new IllegalArgumentException();
        }
        final SceneSettings ss = sceneSettingService.readSceneSetting(in);
        if (ss == null) {
            throw new ResultNotFoundException("Movie settin with id " + in + " not found");
        }
        return ss;
    }
}
