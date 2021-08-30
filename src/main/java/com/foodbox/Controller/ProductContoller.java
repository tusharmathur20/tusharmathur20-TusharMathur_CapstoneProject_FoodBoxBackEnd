package com.foodbox.Controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import com.foodbox.Service.ProductService;
import com.foodbox.entity.two.Product;

@RestController
@RequestMapping("/product")
@CrossOrigin("*")
public class ProductContoller {

 @Autowired
 private ProductService productService;
	
 @RequestMapping (value="/add", method=RequestMethod.POST)
	public Product addProductPost(@RequestBody Product product) {
		return productService.save(product);
	}
	
 @RequestMapping(value="/add/image", method=RequestMethod.POST)
	public ResponseEntity<?> upload(
			@RequestParam("id") Long id,
			HttpServletResponse response, HttpServletRequest request
			){
		try {
			Optional<Product> product = productService.findOne(id);
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Iterator<String> it = multipartRequest.getFileNames();
			MultipartFile multipartFile = multipartRequest.getFile(it.next());
			String fileName = id+".png";
			
			
			byte[] bytes = multipartFile.getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/product/"+fileName)));
			stream.write(bytes);
			stream.close();
			
			return new ResponseEntity("Upload Success!", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity("Upload failed!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/update/image", method=RequestMethod.POST)
	public ResponseEntity updateImagePost(
			@RequestParam("id") Long id,
			HttpServletResponse response, HttpServletRequest request
			){
		try {
			Optional<Product> product =productService.findOne(id);
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Iterator<String> it = multipartRequest.getFileNames();
			MultipartFile multipartFile = multipartRequest.getFile(it.next());
			String fileName = id+".png";
			
			Files.delete(Paths.get("src/main/resources/static/image/product/"+fileName));
			
			byte[] bytes = multipartFile.getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/product/"+fileName)));
			stream.write(bytes);
			stream.close();
			
			return new ResponseEntity("Upload Success!", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity("Upload failed!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping("/productList")
	public List<Product> getproductList() {
		return productService.findAll();
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public Product updateProductPost(@RequestBody Product product) {
		return productService.save(product);
	}
	
	
	@RequestMapping("/{id}")
	public Optional<Product> getProduct(@PathVariable("id") Long id){
		Optional<Product> product = productService.findOne(id);
		return product;
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public ResponseEntity remove(
			@RequestBody String id
			) throws IOException {
		productService.removeOne(Long.parseLong(id));
		try {
		String fileName = id+".png";
		
		Files.delete(Paths.get("src/main/resources/static/image/product/"+fileName));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity("Remove Success!", HttpStatus.OK);
	}
	
	@RequestMapping(value="/searchProduct", method=RequestMethod.POST)
	public List<Product> searchBook (@RequestBody String keyword) {
		List<Product> productList = productService.blurrySearch(keyword);
		
		return productList;
	}
}
