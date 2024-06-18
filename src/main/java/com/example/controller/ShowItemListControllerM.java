package com.example.controller;

import com.example.domain.Bottom;
import com.example.domain.Set;
import com.example.domain.Top;
import com.example.domain.User;
import com.example.response.ItemTypeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE })
@RequestMapping("/items")
public class ShowItemListControllerM {
    @GetMapping("/{itemType}")
    public ResponseEntity<ItemTypeResponse> getItemDetails(@PathVariable String itemType) {
        ItemTypeResponse response = new ItemTypeResponse();
        List<Object> items = new ArrayList<>();

        if (itemType.equalsIgnoreCase("top")) {
            items.add(createSampleTop(1));
            items.add(createSampleTop(2));
        } else if (itemType.equalsIgnoreCase("bottom")) {
            items.add(createSampleBottom(1));
            items.add(createSampleBottom(2));
        } else if (itemType.equalsIgnoreCase("set")) {
            items.add(createSampleSet(1));
            items.add(createSampleSet(2));
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        response.setItems(items);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private Top createSampleTop(int id) {
        Top top = new Top();
        top.setId(id);
        top.setName("Sample Top " + id);
        top.setDescription("Description for Sample Top " + id);
        top.setPrice(1000 + id * 100);
        top.setImagePath("/images/sample_top_" + id + ".jpg");
        return top;
    }

    private Bottom createSampleBottom(int id) {
        Bottom bottom = new Bottom();
        bottom.setId(id);
        bottom.setName("Sample Bottom " + id);
        bottom.setDescription("Description for Sample Bottom " + id);
        bottom.setPrice(1200 + id * 100);
        bottom.setImagePath("/images/sample_bottom_" + id + ".jpg");
        return bottom;
    }

    private Set createSampleSet(int id) {
        Set set = new Set();
        set.setId(id);
        set.setTopId(id);
        set.setBottomId(id);
        set.setName("Sample Set " + id);
        set.setDescription("Description for Sample Set " + id);
        set.setPrice(2000 + id * 100);
        set.setImagePath("/images/sample_set_" + id + ".jpg");
        return set;
    }

}
