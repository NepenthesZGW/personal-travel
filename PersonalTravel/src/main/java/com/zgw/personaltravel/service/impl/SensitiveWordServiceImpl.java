package com.zgw.personaltravel.service.impl;

import com.zgw.personaltravel.entity.SensitiveWord;
import com.zgw.personaltravel.service.SensitiveWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;


@Service
public class SensitiveWordServiceImpl implements SensitiveWordService {
    private static final String apiKey="Iydq2nBpXMn6SvHg014fc1Pc";
    private static final String secretKey="AGcHh2cQSACcwEr0WHepTGFjmkKaMPhP";


    private static final String url="https://aip.baidubce.com/rest/2.0/solution/v1/text_censor/v2/user_defined?access_token=24.58b3c5c45e194fd365e4fc79ae411e6a.2592000.1587886991.282335-19111289";
    private static final MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
    private static final HttpHeaders requestHeaders =  new HttpHeaders();

    static {
        //设置请求头类型
        requestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    }

    @Autowired
    RestTemplate restTemplate;




    public SensitiveWord analysis(String text) {
        //body
        requestBody.add("text",text );


        ResponseEntity<SensitiveWord> sensitiveWordResponseEntity = restTemplate.postForEntity(url, new HttpEntity<MultiValueMap>(requestBody, requestHeaders), SensitiveWord.class);
        requestBody.clear();
        return sensitiveWordResponseEntity.getBody();
    }

    public String getAccessToken(){
        String tokenUrl="https://aip.baidubce.com/oauth/2.0/token";
        Map<String, String> body = new HashMap<>();
        body.put("grant_type","client_credentials");
        body.put("client_id","Iydq2nBpXMn6SvHg014fc1Pc");
        body.put("client_secret","AGcHh2cQSACcwEr0WHepTGFjmkKaMPhP");
        ResponseEntity<Object> objectResponseEntity = restTemplate.postForEntity(tokenUrl, body, Object.class);
        System.out.println(objectResponseEntity.getBody());
        return "";
    }

//    public static void main(String[] args) throws UnsupportedEncodingException {
//        SensitiveWordServiceImpl sensitiveWordService=new SensitiveWordServiceImpl();
//        sensitiveWordService.analysis("卧槽泥马");
//    }
}