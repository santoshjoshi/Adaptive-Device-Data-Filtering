# Adaptive Device Data Filtering

## Need of framework
    We all know real state needs and demand of (desktop, mobile and tablets). In desktop we have empty of space and generally 
    uses WIFI, where as in mobile real state is limited and mostly uses data connection. Hence the need is very simple that using 
    same code base we need to optimize our templtes( html, css, js) and data being sent to these devices in such a way that unnecessory
    data and templtes are not visible.
    Here we wil only cosider the data part as sending extra data in case of these device may consume extra data pack as well as time.
      
  
##### Understanding Responsive and Adaptive Web Design
    Responsive: In responsive design the client is smart enought to handle the presentation
    part based on the CSS provided to  it.
    
    Adaptive: In adaptive design the server on the basis on devices choose propre template and send to client.
    
To filter out or modify response field based on various device When your code base is same across all devices( Mobile, Tablet and Desktop)

##### About the Framework

The Framework is based on java Reflection.

#### How to use

    public class Person {
        private String name;
        private AddressD address;
        private String aboutMe;
        private String detailedInformation;
    }

#### Anotations Used

    a) @IgnoreData : to ignore the field completely on a device and page
    b) @ModifyData : to modify fields data for a device and page
                     @ModifyData contains another annotation 
                     @ModifySourceData which provides a handler/hook
      for various other operations via operation field, various operations, for example trimming, modification, 
      changing date formats
