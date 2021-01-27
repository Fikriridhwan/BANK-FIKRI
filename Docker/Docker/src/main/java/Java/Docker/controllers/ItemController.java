/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Java.Docker.controllers;

import Java.Docker.entities.Item;
import Java.Docker.services.ItemService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author User
 */
@RequestMapping("api/item")
@RestController
public class ItemController {
    
    ItemService service; 

    @Autowired
    public ItemController(ItemService itemService) {
        this.service = itemService;
    }
    
    @GetMapping("")
    public List<Item> getAll(){
        return service.getAll();
    }
    
    @GetMapping("{id}")
    public Item getById(@PathVariable int id) {
        return service.getById(id);
    }
    
    @PostMapping("/save")
    public Item insert(@RequestBody Item item) {
        Item result = service.save(item);
        return result;
    }
    
    @DeleteMapping("{id}")
    public Map<String,String> delete(@PathVariable int id){
        boolean isSuccess = service.delete(id);
        Map<String, String> status = new HashMap<>();
        if (isSuccess) {
            status.put("status", "200");
            return status;
        }else{
            return status;
        }
    }
    
    
    
    
}
