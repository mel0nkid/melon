package com.xinyuan.assist.service.api.dic;

import com.alibaba.fastjson.JSON;
import com.xinyuan.assist.util.HttpUtil;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@PropertySource("classpath:application.properties")
public class YoudaoService {

    @Value("${dic.youdao}")
    private String serviceUrl;

    private String generateUrl(String word) {
        return serviceUrl + "?"
                + "q=" + word
                + "&keyfrom=dict.top"
                + "&le=eng";
    }

    public TranModel queryHTML(String word) {
        String finalUrl = generateUrl(word);
        System.out.println(finalUrl);
        String html = HttpUtil.doGet(finalUrl);
        return parseFromHTML(html);
    }


    public TranModel parseFromHTML(String html) {
        TranModel tranModel = new TranModel();
        if (StringUtils.isBlank(html)) {
            return tranModel;
        }
        Document doc = Jsoup.parse(html);
        Element phraseDom = doc.select("#phrsListTab").first();

        // 获取图片  #picUgcImg
        String img = phraseDom.select(".img-list > a >img").attr("src");
        tranModel.setImg(img);

        // keyword
        String keyword = phraseDom.select("#phrsListTab > h2 > span").text();
        tranModel.setKeyword(keyword);

        // 发音
        Elements paraphraseEles = phraseDom.select("#phrsListTab > h2 > div > span");
        List<String> pronounces = parsePronounces(paraphraseEles);
        tranModel.setPronounces(pronounces);

        // #webTrans  网络释义
        Element transDom = doc.select("#webTrans").first();
        List<ParaphraseModel> paraphrases = parseParaphrase(transDom);
        tranModel.setParaphrases(paraphrases);

        // #webPhrase > p 短语
        Elements phraseEles = transDom.select("#webPhrase > p");
        List<PhraseModel> phrases = parsePhrases(phraseEles);
        tranModel.setPhrases(phrases);

        // #examples 例句
        Element exampleEle = doc.select("#examples").first();
        List<ExamSentModel> examples = parseExamples(exampleEle);
        tranModel.setExampleSentences(examples);
        return tranModel;
    }

    /**
     * 例句
     *
     * @param exampleEle
     * @return
     */
    private List<ExamSentModel> parseExamples(Element exampleEle) {
        List<ExamSentModel> lis = new ArrayList<>();
        if (exampleEle == null) {
            return lis;
        }
        Elements exampleEles = exampleEle.select("div#bilingual > ul > li");
        for (Element ele : exampleEles) {
            // #bilingual > ul > li:nth-child(1)  #examplesToggle
            // 解析英文例句
            Element enSentenceEls = ele.select("p:nth-child(1)").first();
            String enSentence = parseSentence(enSentenceEls, true);
            Element cnSentenceEls = ele.select("p:nth-child(2)").first();
            String cnSentence = parseSentence(cnSentenceEls, false);
            String source = ele.select("p:nth-child(3) > a").text();
            ExamSentModel examSent = new ExamSentModel(source, enSentence, cnSentence);
            lis.add(examSent);
        }
        return lis;
    }

    private String parseSentence(Element sentence, boolean isEn) {
        Elements spanEls = sentence.select("span");
        StringBuilder content = new StringBuilder();
        String appendStr = "";
        if(isEn) {
            appendStr = " ";
        }
        for (Element span : spanEls) {
            content.append(span.text().trim()).append(appendStr);
        }
        return content.toString();
    }

    /**
     * 解析短语
     *
     * @param phraseEles
     * @return
     */
    private List<PhraseModel> parsePhrases(Elements phraseEles) {
        List<PhraseModel> lis = new ArrayList<>();
        if (phraseEles == null) {
            return lis;
        }
        for (Element ele : phraseEles) {
            String content = ele.text();
            String title = ele.select("span > a").first().text();
            PhraseModel model = new PhraseModel(title, content);
            lis.add(model);
        }
        return lis;
    }


    /**
     * 解析发音
     *
     * @return
     */
    public List<String> parsePronounces(Elements phrases) {
        List<String> lis = new ArrayList<>();
        if (phrases == null) {
            return lis;
        }
        for (Element ele : phrases) {
            String flag = ele.text();
            String pronounce = ele.select("span").first().text();
            lis.add(flag + " " + pronounce);
        }
        return lis;
    }

    public List<ParaphraseModel> parseParaphrase(Element phrase) {
        List<ParaphraseModel> lis = new ArrayList<>();
        if (phrase == null) {
            return lis;
        }
        Elements paraPhrases = phrase.select(" #tWebTrans > div.wt-container");
        for (Element ele : paraPhrases) {
            String title = ele.select(".title > span").text();
            String content = ele.select(".collapse-content").text();
            ParaphraseModel paraphrase = new ParaphraseModel(title, content);
            lis.add(paraphrase);
        }
        return lis;
    }

    public static void main(String[] args) {
        YoudaoService service = new YoudaoService();
        service.serviceUrl = "http://dict.youdao.com/search";
        TranModel ret = service.queryHTML("a11");
        System.out.println(JSON.toJSONString(ret));
    }
}
