## CÁC PHƯƠNG THỨC TÌM KIẾM CƠ BẢN

#URL: https://demoqa.com/text-box

1. css selector: [class='form-label']
2. xpath : //label[@class='form-label']

#08 cách tìm kiếm 
1. By ID: 
   1. XPath: //input[@id='userName']
   2. CSS: input[id='userName'] or #userName <br/>
      (Sử dụng # - Đại diện cho cách tìm kiếm với ID)
2. By Name:
   1. XPath: //meta[@name='viewport']
   2. CSS: meta[name='viewport'] 
3. By Class Name
   1. XPath: //label[@class='form-label']
   2. CSS: label[class='form-label'] or .form-label <br/>
      (Sử dụng . - Đại diện cho class)
4. By Tag Name
   1. XPath: //label
   2. CSS: label
5. By Link Text : Sử dụng text được hiển thị trên Link để tương tác với phần tử
6. By Partial Link Text : Giống với Link Text, tuy nhiên sẽ chỉ cần trùng khớp một phần (contains)