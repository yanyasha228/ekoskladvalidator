package com.ekoskladvalidator.Controllers;

import com.ekoskladvalidator.Models.Product;
import com.ekoskladvalidator.Services.GroupService;
import com.ekoskladvalidator.Services.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductsController {

    private static final Logger logger = Logger.getLogger(ProductsController.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private GroupService groupService;


//    @Autowired
//    private ProductListPageHelper productListPageHelper;


    @GetMapping
    public String productsList(Model model,
                               @PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC, size = 5) Pageable pageable,
                               @RequestParam Optional<Boolean> dataForValidatingExist,
                               @RequestParam Optional<Integer> groupId,
                               @RequestParam Optional<String> nonFullProductName) {


        /**
         * Delegate showing product list logic into the {@link ProductListPageHelper}
         */
//        productListPageHelper.validatePageShowingState(productsPageNumber, productsPageSize, productsStockSorting, productsCategorySortingId , productNameSearchInput);
        Page<Product> productsPage = productService.findProductsWithPagination(nonFullProductName.orElse(""),
                groupService.findById(groupId.orElse(0)).orElse(null),
                dataForValidatingExist.orElse(null),
                pageable);

//        Double eurCurrency = adminSettings.getEurCurrency();
//        Double usdCurrency = adminSettings.getUsdCurrency();

        model.addAttribute("dataForValidatingExist", dataForValidatingExist.orElse(null));

        model.addAttribute("groupId", groupId.orElse(0));

        model.addAttribute("nonFullProductName", nonFullProductName.orElse(""));

        model.addAttribute("productsPage",
                productsPage);
//            model.addAttribute("productsList",
//                    productService.findProductsByProductStockWithPagination(productListPageHelper.getProductsStockSorting(), PageRequest.of(productListPageHelper.getCurrentPageNumber(),
//                            productListPageHelper.getPageSize(),
//                            Sort.Direction.ASC,
//                            "id")));

        model.addAttribute("groups", groupService.findAll());

        return "products";
    }


}

