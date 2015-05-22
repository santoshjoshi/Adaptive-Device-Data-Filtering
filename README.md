# Adaptive Device Data Filtering

## Need of framework
   
  
#####Understanding Responsive and Adaptive Web Design
    Responsive: in responsive design the client is smart enought to handle the presentation
    part based on the CSS provided to  it.
    
To filter out or modify response field based on various device When your code base is same across all devices( Mobile, Tablet and Desktop)

##### About the Framework

The Framework is based on java Reflection.

#####Features and Extensions


####How to use

public class Person {
    private String name;
    private AddressD address;
    private String aboutMe;
    private String detailedInformation;
}

####Anotations Used

a) @IgnoreData : to ignore the field completely on a device and page
b) @ModifyData : to modify fields data for a device and page
      @ModifyData contains another annotation @ModifySourceData which provides a handler/hook
      for various other operations via operation field, various operations, for example trimming, modification, 
      changing date formats
