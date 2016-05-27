/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.jeremywright.myapplication.jokebackend;

import com.example.Jokes;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

//Code taken from https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints

/** An endpoint class we are exposing */
@Api(
  name = "myApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "jokebackend.myapplication.jeremywright.example.com",
    ownerName = "jokebackend.myapplication.jeremywright.example.com",
    packagePath=""
  )
)
public class MyEndpoint {

    /** A simple endpoint method that returns a joke */
    @ApiMethod(name = "getJoke")
    public MyBean getJoke() {
        MyBean response = new MyBean();

        response.setData(Jokes.getJoke());

        return response;
    }

}
