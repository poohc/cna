package com.dev.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;


@Controller
@RequestMapping("upload")
public class Upload {
    
    @RequestMapping(value = "imageFileUpload")
    public ModelAndView imageFileUpload(HttpServletRequest request, HttpServletResponse response) throws IOException{
        
        BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
        ModelAndView mav = new ModelAndView("main/redirectPage");
        
        Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(request);
        List<BlobKey> blobKeys = blobs.get("files");
        
        if (blobKeys == null || blobKeys.isEmpty()) {
            mav.addObject("url","/cna/main.do");
        } else {
            
            DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
            
            for(BlobKey blobKey : blobKeys){
                Entity bKey = new Entity("bKey");
                bKey.setProperty("blobKey", blobKey.getKeyString());
                datastore.put(bKey);
            }
            
            mav.addObject("url","/cna/main.do");
        }
        
        return mav;
    }      
}