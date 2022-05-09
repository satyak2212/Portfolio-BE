package com.portfolio.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.portfolio.model.Portfolio;
import com.portfolio.repository.PortfolioRepository;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class PortfolioController {

	@Autowired
	private PortfolioRepository portRepo;
	
	private List<Portfolio> portfolios = createList();

	private static List<Portfolio> createList() {
		List<Portfolio> portfolioList = new ArrayList<>();

		Portfolio portfolio1 = new Portfolio();
		portfolio1.setRegNum("1001");
		portfolio1.setName("The Godfather");
		portfolio1.setAuthor("Mario Puzo");
		portfolio1.setPrice(10);

		Portfolio portfolio2 = new Portfolio();
		portfolio1.setRegNum("1002");
		portfolio2.setName("The Fellowship of the Ring");
		portfolio2.setAuthor("J.R.R. Tolkien");
		portfolio2.setPrice(15);

		portfolioList.add(portfolio1);
		portfolioList.add(portfolio2);

		return portfolioList;
	}
	
	@GetMapping("/portfolios")
	public List<Portfolio> getAllPortfolios() {
		portfolios = portRepo.findAll();
		return portfolios;
	}

	@PostMapping("/portfolios")
	public Portfolio createPortfolio(@RequestBody Portfolio portfolio) {
		System.out.println("Added Portfolio - " + portfolio.getName());
		portRepo.insert(portfolio);
		//portfolios.add(portfolio);
		return portfolio;
	}

	@PutMapping("/portfolios/{regNum}")
	public Portfolio updatePortfolio(@PathVariable(value = "regNum") String name, @RequestBody Portfolio portfolioDetails) {
		System.out.println("Updated Portfolio - " + name);
//		for (Portfolio portfolio : portfolios) {
//			if (portfolio.getName().equals(name)) {
//				portfolios.remove(portfolios.indexOf(portfolio));
//				portfolios.add(portfolioDetails);
//				break;
//			}
//		}
		portRepo.deleteById(portfolioDetails.getRegNum());
		portRepo.save(portfolioDetails);
		return portfolioDetails;
	}

	@DeleteMapping("/portfolios/{regNum}")
	public Portfolio deletePortfolio(@PathVariable(value = "regNum") String regNum) {
		System.out.println("Deleted Portfolio - " + regNum);
		Portfolio deletedPortfolio = null;
//		for (Portfolio portfolio : portfolios) {
//			if (portfolio.getName().equals(name)) {
//				portfolios.remove(portfolio);
//				deletedPortfolio = portfolio;
//				break;
//			}
//		}
		portRepo.deleteById(regNum);
		return deletedPortfolio;
	}
}
