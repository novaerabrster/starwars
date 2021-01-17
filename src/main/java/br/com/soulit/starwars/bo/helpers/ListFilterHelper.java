package br.com.soulit.starwars.bo.helpers;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.PredicateUtils;
import org.springframework.stereotype.Component;

@Component
public class ListFilterHelper implements Serializable {
    
    private static final long serialVersionUID = -8312561906193794559L;

    public List<Integer> filterList(final List<Integer> masterList, final List<Integer> childList, int actualIndex) {
        int nextIndex = actualIndex + 1;
        final Integer firstIndex = masterList.get(actualIndex);
        if (nextIndex == masterList.size()) {
            nextIndex = actualIndex;
        }
        final Integer lastIndex = masterList.get(nextIndex);
        List<Integer> filteredList;
        if (actualIndex != nextIndex) {
            filteredList = filterListBetween(childList, firstIndex, lastIndex);
        } else {
            filteredList = filterListGreater(childList, firstIndex);
        }
        return filteredList;
    }
    
    private List<Integer> filterListBetween(List<Integer> fullList, final Integer firstIndex, final Integer lastIndex) {
        final Predicate predicate = PredicateUtils.allPredicate(new Predicate[] { new Predicate() {
            
            @Override
            public boolean evaluate(Object obj) {
                if (obj == null) {
                    return false;
                }
                return ((Integer) obj).intValue() > firstIndex;
            }
        }, new Predicate() {
            
            @Override
            public boolean evaluate(Object obj) {
                if (obj == null) {
                    return false;
                }
                return ((Integer) obj).intValue() < lastIndex;
            }
        } });
        return (List<Integer>) CollectionUtils.select(fullList, predicate);
    }
    
    private List<Integer> filterListGreater(List<Integer> fullList, final Integer referenceIndex) {
        final Predicate predicate = new Predicate() {
            
            @Override
            public boolean evaluate(Object obj) {
                if (obj == null) {
                    return false;
                }
                return ((Integer) obj).intValue() > referenceIndex;
            }
        };
        return (List<Integer>) CollectionUtils.select(fullList, predicate);
    }
}
