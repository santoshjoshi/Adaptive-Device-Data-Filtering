# Adaptive Device Data Filtering

## Table of Contents

- [Introduction](#introduction)
- [Responsive vs. Adaptive Design](#responsive-vs-adaptive-design)
- [Annotations for Optimizing Data](#annotations-for-optimizing-data)

    
## Introduction
   As technology advances, it's crucial to optimize real estate websites for different devices such as desktops, mobiles, and tablets. Desktops offer more space and typically use WIFI, while mobile devices have limited space and rely on data connections. To meet these varying needs, it's important to use the same code base and optimize templates (HTML, CSS, JS) and data being sent to these devices to ensure that unnecessary data and templates are not visible.

To achieve this, it's important to consider the amount of data being sent to these devices, as sending extra data can consume additional data packs and time. By optimizing the code and data, you can ensure that users have a seamless experience regardless of the device they're using to access the real estate website.
      
  
##### Responsive vs Adaptive Design

**Responsive** and **Adaptive** design are two approaches used to optimize websites for different devices. 

**Responsive** design uses CSS to adjust the website's layout and presentation based on the screen size of the device being used. This means that the client device is responsible for handling the presentation based on the CSS provided to it. With responsive design, the website looks the same across different devices, but the layout and presentation may differ.

On the other hand, **Adaptive design** uses server-side technology to detect the device being used and sends a specific version of the website designed for that device. In adaptive design, the server selects the appropriate template for the device and sends it to the client. This approach results in a more tailored user experience, but it requires creating multiple templates for different devices, which can be time-consuming.

In conclusion, both responsive and adaptive designs have their advantages and disadvantages. Responsive design offers a more consistent user experience across different devices, while adaptive design offers a more customized experience. The choice between the two depends on the specific needs and goals of the website.
    
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

#### Annotations for Optimizing Data

Annotations are used to add metadata to code, which can provide additional information for compilers, frameworks, or other tools. In web development, annotations can be used to optimize websites for different devices by modifying or ignoring certain data.

Two examples of annotations that can be used for this purpose are `@IgnoreData` and `@ModifyData`. The `@IgnoreData` annotation allows developers to ignore a field completely on a device and page, which can help to reduce unnecessary data being sent to the client. This can lead to faster page load times and a better user experience on devices with limited bandwidth.

The `@ModifyData` annotation, on the other hand, allows developers to modify field data for a device and page. This annotation contains another annotation called `@ModifySourceData`, which provides a handler or hook for various operations such as trimming, modification, or changing date formats. This can be useful for customizing data to fit the specific needs of a device or page, such as displaying dates in a different format or trimming long strings of text.

Overall, annotations such as `@IgnoreData` and `@ModifyData` can be powerful tools for optimizing web development for different devices, allowing developers to customize data and reduce unnecessary data transfer to improve the performance and user experience of their websites.
