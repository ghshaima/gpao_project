package com.helloworld.demoproject.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.helloworld.demoproject.entities.article;
import com.helloworld.demoproject.entites.Client;
import com.helloworld.demoproject.entites.CommandeClient;
import com.helloworld.demoproject.entites.LigneCommandeClient;
import com.helloworld.demoproject.models.ModelCommandeClient;
import com.helloworld.demoproject.models.StringResponse;
import com.helloworld.demoproject.services.IArticleService;
import com.helloworld.demoproject.services.IClientService;
import com.helloworld.demoproject.services.ICommandeClientService;
import com.helloworld.demoproject.services.ILigneCommandeClientService;


@Controller
@RequestMapping(value = "/ofClient")


public class OfClientController {
	
	@Autowired
	private ICommandeClientService commandeService;
	
	@Autowired
	private ILigneCommandeClientService ligneCdeService;
	
	@Autowired
	private IClientService clientService;
	
	@Autowired
	private IArticleService articleService;
	
	@Autowired
	private ModelCommandeClient modelCommande;
	
	@RequestMapping(value = "/")
	public String index(Model model) {
		List<CommandeClient> commandesClient = commandeService.selectAll();
		if (commandesClient.isEmpty()) {
			commandesClient = new ArrayList<CommandeClient>();
		} else {
			for (CommandeClient commandeClient : commandesClient) {
				List<LigneCommandeClient> ligneCdeClt = ligneCdeService.getByIdCommande(commandeClient.getIdCommandeClient());
				commandeClient.setLigneCommandeClients(ligneCdeClt);
			}
		}
		
		model.addAttribute("commandesClient", commandesClient);
		return "commandeclient/commandeclient";
	}
	
	@RequestMapping(value = "/nouveau")
	public String nouvelleCommande(Model model) {
		List<Client> clients = clientService.selectAll();
		if (clients.isEmpty()) {
			clients = new ArrayList<Client>();
		}
		modelCommande.creerCommande();
		model.addAttribute("code", modelCommande.getCommande().getCode());
		model.addAttribute("dateCde", modelCommande.getCommande().getDateCommande());
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
