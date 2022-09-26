package com.sena.disocc.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sena.disocc.modelo.Usuario;
import com.sena.disocc.service.UsuarioService;

@Controller
@RequestMapping("/Dashboard/admin")
public class UsuarioController {

	@Autowired
	UsuarioService ususervice;
	
	@GetMapping("/usuario")
	public String list(Model modelo) {
		modelo.addAttribute("usuario", ususervice.listAllUsuarios());
		return "Dashboard/usuarios/usuarioListar";
	}

	@GetMapping("/usuario/nuevo")
	public String formularioGuardar(Model modelo) {
		Usuario usuario = new Usuario();
		modelo.addAttribute("usuario", usuario);
		return "Dashboard/usuarios/usuarioCrear";
	}

	@PostMapping("/usuario")
	public String guardar(@ModelAttribute("usuario") Usuario usuario) {
		ususervice.saveUsuario(usuario);
		return "redirect:/Dashboard/usuarios/usuarioListar";
	}
	
	@GetMapping("/usuario/editar/{id}")
	public String formularioEditar(@PathVariable int id, Model modelo) {
		modelo.addAttribute("usuario", ususervice.findById(id));
		return "Dashboard/usuarios/usuarioEditar";
	}
	
	@PostMapping("/usuario/{id}")
	public String editar(@PathVariable int id, @ModelAttribute("usuario") Usuario usuario) {
		Usuario usuarioExistente = ususervice.findById(id);
		usuarioExistente.setIdUsuario(id);
		usuarioExistente.setContrasena(usuario.getContrasena());
		usuarioExistente.setCorreo(usuario.getCorreo());
		usuarioExistente.setDireccion(usuario.getDireccion());
		usuarioExistente.setNumeroDocumento(usuario.getNumeroDocumento());
		usuarioExistente.setPrimerApellido(usuario.getPrimerApellido());
		usuarioExistente.setPrimerNombre(usuario.getPrimerNombre());
		usuarioExistente.setSegundoApellido(usuario.getSegundoApellido());
		usuarioExistente.setSegundoNombre(usuario.getSegundoNombre());
		usuarioExistente.setTelefono(usuario.getTelefono());
		usuarioExistente.setTipoDocumento(usuario.getTipoDocumento());
		usuarioExistente.setIdRol(usuario.getIdRol());
		usuarioExistente.setIdEstado(usuario.getIdEstado());
		ususervice.saveUsuario(usuarioExistente);
		return "redirect:/Dashboard/usuarios/usuarioListar";
	}
	
	@GetMapping("/usuario/{id}")
	public String eliminar(@PathVariable int id) {
		ususervice.deleteUsuario(id);
		return "redirect:/Dashboard/usuarios/usuarioListar";
	}
}
