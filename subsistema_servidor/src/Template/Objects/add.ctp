<?php
/**
 * @var \App\View\AppView $this
 * @var \App\Model\Entity\Object $object
 */
?>
<div class="tile">
    <div class="tile-body">
        <div class="objects form large-9 medium-8 columns content">
            <?= $this->Form->create($object, ['type' => 'file']) ?>
            <fieldset>
                <legend><?= __('Cadastrar Objeto') ?></legend>
                <div class="row">
                    <div class="col-md-6">
                        <?= $this->Form->control('user_id', ['type' => 'hidden', 'value' => $this->request->session()->read('Auth.User.id')]); ?>
                        <?= $this->Form->control('title', ['label' => 'Título', 'class' => 'form-control']); ?>
                    </div>
                    <div class="col-md-6">
                        <?= $this->Form->control('solved', ['label' => 'Situação do Objeto', 'options' => ['Não resolvido' => 'Não resolvido', 'Resolvido' => 'Resolvido'], 'class' => 'form-control']); ?>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <?= $this->Form->control('type', ['label' => 'Classificação', 'options' => ['Encontrado' => 'Encontrado', 'Perdido' => 'Perdido'], 'class' => 'form-control']); ?>
                    </div>
                    <div class="col-md-6">
                        <?= $this->Form->control('date', ['label' => 'Data do Ocorrido', 'type' => 'text', 'class' => 'form-control']); ?>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <?= $this->Form->control('image', ['label' => 'Selecione uma imagem', 'type' => 'file', 'class' => 'form-control']); ?>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <?= $this->Form->control('description', ['label' => 'Descrição do Ocorrido' ,'class' => 'form-control']); ?>
                    </div>
                </div>
            </fieldset>
            <center><?= $this->Form->button(__('Salvar'), ['class' => 'btn btn-primary form-btn']) ?></center>
            <?= $this->Form->end() ?>
        </div>
    </div>
</div>