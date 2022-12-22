package top.hubby.dp.adapter.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zack <br>
 * @create 2022-12-22 23:20 <br>
 * @project practice-optimize <br>
 */
public class ASensitiveWordsFilter {
    // text是原始文本，函数输出用***替换敏感词之后的文本
    public String filterSexyWords(String text) {
        return null;
    }

    public String filterPoliticalWords(String text) {
        return null;
    }
}

class BSensitiveWordsFilter { // B敏感词过滤系统提供的接口
    public String filter(String text) {
        return null;
    }
}

class RiskManagement {
    private ASensitiveWordsFilter aFilter = new ASensitiveWordsFilter();
    private BSensitiveWordsFilter bFilter = new BSensitiveWordsFilter();

    public String filterSensitiveWords(String text) {
        String maskedText = aFilter.filterSexyWords(text);
        maskedText = aFilter.filterPoliticalWords(maskedText);
        maskedText = bFilter.filter(maskedText);
        return maskedText;
    }
}

/******************* after *****************/



interface ISensitiveWordsFilter { // 统一接口定义
    String filter(String text);
}

class ASensitiveWordsFilterAdaptor implements ISensitiveWordsFilter {
    private ASensitiveWordsFilter aFilter;

    @Override
    public String filter(String text) {
        String maskedText = aFilter.filterSexyWords(text);
        maskedText = aFilter.filterPoliticalWords(maskedText);
        return maskedText;
    }
}

class BSensitiveWordsFilterAdaptor implements ISensitiveWordsFilter {
    private BSensitiveWordsFilter aFilter;

    @Override
    public String filter(String text) {
        return aFilter.filter(text);
    }
}

// 扩展性更好，更加符合开闭原则，如果添加一个新的敏感词过滤系统，
// 这个类完全不需要改动；而且基于接口而非实现编程，代码的可测试性更好。
class RiskManagementV2 {
    private List<ISensitiveWordsFilter> filters = new ArrayList<>();

    public void addSensitiveWordsFilter(ISensitiveWordsFilter filter) {
        filters.add(filter);
    }

    public String filterSensitiveWords(String text) {
        String maskedText = text;
        for (ISensitiveWordsFilter filter : filters) {
            maskedText = filter.filter(maskedText);
        }
        return maskedText;
    }
}
