# Space-Apps aircheck #

## Build & Run ##

```sh
$ cd aircheck
$ ./sbt
> jetty:start
> browse
```

If `browse` doesn't launch your browser, open manually [http://localhost:8080/](http://localhost:8080/) in your browser.

### cartoDB

We have used the location intelligence and visualization engine **cartoDB**  
to display the world maps that our application shows. This engine works  
as a database of geographical points associated to the values that we  
want to show in an aesthecally way.

Basically we present to the user two different maps:

* **Heatmap**
 
This map informs the user how the air quality is in a 0-10 scale. With this  
heatmap, the user can easily view the zones with the best or worst air  
quality, or just check and compare the zones the user wants.  

* **Iconmap**

The second type is an iconmap, where icons are enviromental events such as  
a tornado or an eruption of a volcano. This way the user can view in a  
blink what is happening around all the world.  

![](https://github.com/wynro/spaceapps-web/blob/master/src/res/images/IconMap.png)
