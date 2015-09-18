package com.dev.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;

@Controller
@RequestMapping("cna")
public class MainController {
    
    @RequestMapping(value = "main")    
    public ModelAndView main(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("main/main");
        return mav;
    }
    
    @RequestMapping(value = "gallery")    
    public ModelAndView gallery(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("main/gallery");
        
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        
        Query query = new Query("bKey");
        PreparedQuery pq = datastore.prepare(query);
        
        List<String> blobKeyList = new ArrayList<String>();
        List<String> imgUrlList = new ArrayList<String>();
        List<String> imgThumbList = new ArrayList<String>();
        
        for(Entity result : pq.asIterable()){
            blobKeyList.add(result.getProperty("blobKey").toString());
        }
        
        ImagesService imagesService = ImagesServiceFactory.getImagesService();
        
        for(String blobKey : blobKeyList){
            BlobKey bKey = new BlobKey(blobKey);
            imgThumbList.add(imagesService.getServingUrl(bKey)+"=s120");
            imgUrlList.add(imagesService.getServingUrl(bKey));
        }
        
        mav.addObject("imgThumbList",imgThumbList);
        mav.addObject("imgUrlList",imgUrlList);
        
        return mav;
    }
    
    @RequestMapping(value = "imageUpload")    
    public ModelAndView imageUpload(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("main/imageUpload");
        return mav;
    }
}
