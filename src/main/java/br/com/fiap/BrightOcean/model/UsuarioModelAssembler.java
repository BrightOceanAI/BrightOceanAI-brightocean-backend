package br.com.fiap.BrightOcean.model;


import br.com.fiap.BrightOcean.controller.UsuarioController;
import org.springframework.hateoas.EntityModel;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UsuarioModelAssembler extends RepresentationModelAssemblerSupport<Usuario, EntityModel<Usuario>> {

    public UsuarioModelAssembler() {
        super(UsuarioController.class, (Class<EntityModel<Usuario>>)(Class<?>)EntityModel.class);
    }


    @Override
    public EntityModel<Usuario> toModel(Usuario usuario) {
        EntityModel<Usuario> usuarioModel = EntityModel.of(usuario,
                linkTo(methodOn(UsuarioController.class).buscarPorEmail(usuario.getEmail())).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).listar()).withRel("usuarios"));

        return usuarioModel;
    }
}