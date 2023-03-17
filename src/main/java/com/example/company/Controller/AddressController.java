package com.example.company.Controller;

import com.example.company.Dto.AddressDto;
import com.example.company.Entity.Address;
import com.example.company.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    AddressService addressService;

    @GetMapping
    public ResponseEntity<?> getAddress() {
        List<Address> addressList = addressService.getAddress();
        if (addressList != null) return ResponseEntity.ok().body(addressList);
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Result> postAddress(@RequestBody AddressDto addressDto) {
        Result result = addressService.postAddress(addressDto);
        if (result.getActive()) {
            return ResponseEntity.ok().body(result);
        }
        return ResponseEntity.status(406).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> putAddress(@PathVariable Integer id, @RequestBody AddressDto addressDto) {
        Result result = addressService.putAddress(id, addressDto);
        if (result.getActive()) {
            return ResponseEntity.ok().body(result);
        }
        return ResponseEntity.status(204).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteAddress(@PathVariable Integer id) {
        Result result = addressService.deleteAddress(id);
        if (result.getActive()) {
            return ResponseEntity.ok().body(result);
        }
        return ResponseEntity.status(204).body(result);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
