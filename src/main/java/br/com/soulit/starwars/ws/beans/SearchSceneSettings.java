package br.com.soulit.starwars.ws.beans;

import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.soulit.starwars.bo.beans.ISceneSettingService;
import br.com.soulit.starwars.commons.ResultNotFoundException;
import br.com.soulit.starwars.ws.response.SceneSettings;

@Service
public class SearchSceneSettings extends AbstractRestService<Object, List<SceneSettings>>
        implements ISearchSceneSettings {
        
    private static final long    serialVersionUID = 5801866297471286332L;

    @Autowired
    private ISceneSettingService sceneSettingService;
                                 
    @Override
    protected List<SceneSettings> process(Object in) throws Exception {
        final List<SceneSettings> all = sceneSettingService.listAllSettings();
        if (CollectionUtils.isEmpty(all)) {
            throw new ResultNotFoundException("No movie settings found");
        }
        return all;
    }
}
