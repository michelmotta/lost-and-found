<?php
/**
 * @var \App\View\AppView $this
 * @var \App\Model\Entity\User $user
 */
?>
<div class="tile">
    <div class="tile-body">
        <div class="users form large-9 medium-8 columns content">
            <?= $this->Form->create($user) ?>
            <fieldset>
                <legend><?= __('Editar Usuário') ?></legend>
                <?php
                    echo $this->Form->control('username', ['label' => 'Nome de Usuário', 'class' => 'form-control']);
                    echo $this->Form->control('email', ['label' => 'E-mail', 'class' => 'form-control']);
                    echo $this->Form->control('password', ['label' => 'Senha', 'class' => 'form-control']);
                ?>
            </fieldset>
            <center><?= $this->Form->button(__('Salvar'), ['class' => 'btn btn-primary form-btn']) ?></center>
            <?= $this->Form->end() ?>
        </div>
    </div>
</div>