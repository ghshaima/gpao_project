package com.helloworld.demoproject.Controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.helloworld.demoproject.entities.Article;
import com.helloworld.demoproject.entities.Client;
import com.helloworld.demoproject.entities.Of;

import com.helloworld.demoproject.models.OfClient;
import com.helloworld.demoproject.models.StringResponse;
import com.helloworld.demoproject.services.ArticleService;
import com.helloworld.demoproject.services.ClientService;
import com.helloworld.demoproject.services.OfService;



@Controller
@RequestMapping(value = "/ofClient")


public class OfClientController {
	
	@Autowired
	private OfService commandeService;
	
	
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private OfClient ofclient;
	
	@RequestMapping(value = "/")
	public String index(Model model) {
		List<Of> of = commandeService.selectAll();
		if (Of.isEmpty()) {
			of = new ArrayList<Of>();
		} else {
			for (OfClient commandeClient : ofclient) {
				List<OfClient> ligneCdeClt = ligneCdeService.getByIdCommande(commandeClient.getIdCommandeClient());
				commandeClient.setLigneCommandeClients(ligneCdeClt);
			}
		}
		
		model.addAttribute("ofClient", ofclient);
		return "commandeclient/commandeclient";
	}
	
	@RequestMapping(value = "/nouveau")
	public String nouvelleCommande(Model model) {
		List<Client> clients = clientService.selectAll();
		if (clients.isEmpty()) {
			clients = new ArrayList<Client>();
		}
		OfClient.creerOfClient();
		model.addAttribute("code", OfClient.getOfClient().getCode());
		model.addAttribute("dateCde", OfClient.getOfClient().getDateOf());
		model.addAttribute("clients", clients);
		return "commandeclient/nouvellecommande";
	}
	
	@RequestMapping(value = "/creerCommande")
	@ResponseBody
	public String creerCommande(final Long idClient) {
		if (idClient == null) {
			return null;
		}
		Client client = clientService.getById(idClient);
		if (client == null) {
			return null;
		}
		
		return "OK";
	}
	
	@RequestMapping(value = "/ajouterLigne")
	@ResponseBody
	public LigneCommandeClient getArticleByCode(final Long codeArticle) {
		if (codeArticle == null) {
			return null;
		}
		Article article = articleService.findOne("codeArticle", ""+codeArticle);
		if (article == null) {
			return null;
		}
		return modelCommande.ajouterLigneCommande(article); 
	}
	
	@RequestMapping(value = "/supprimerLigne")
	@ResponseBody
	public LigneCommandeClient supprimerLigneCommande(final Long idArticle) {
		if (idArticle == null) {
			return null;
		}
		Article article = articleService.getById(idArticle);
		if (article == null) {
			return null;
		}
		return modelCommande.supprimerLigneCommande(article);
	}
	
	@RequestMapping(value = "/enrigstrerCommande", produces = "application/json")
	@ResponseBody
	public StringResponse enrgistrerCommande(HttpServletRequest request) {
		CommandeClient nouvelleCommande = null;
		if (modelCommande.getCommande() != null) {
			nouvelleCommande = commandeService.save(modelCommande.getCommande());
		}
		if (nouvelleCommande != null) {
			Collection<LigneCommandeClient> ligneCommandes = modelCommande.getLignesCommandeClient(nouvelleCommande);
			if (ligneCommandes != null && !ligneCommandes.isEmpty()) {
				for (LigneCommandeClient ligneCommandeClient : ligneCommandes) {
					ligneCdeService.save(ligneCommandeClient);
				}
				modelCommande.init();
			}
		}
		
		return new StringResponse(request.getContextPath() + "/commandeclient");
	}

}
