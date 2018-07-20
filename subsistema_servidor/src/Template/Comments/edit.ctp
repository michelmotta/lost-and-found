<?php
/**
 * @var \App\View\AppView $this
 * @var \App\Model\Entity\Comment $comment
 */
?>
<div class="tile">
    <div class="tile-body">
        <div class="comments form large-9 medium-8 columns content">
            <?= $this->Form->create($comment) ?>
            <fieldset>
                <legend><?= __('Editar Comentário') ?></legend>
                <?php
                    echo $this->Form->control('object_id', ['label' => 'Nome do Objeto', 'options' => $objects, 'class' => 'form-control']);
                    echo $this->Form->control('user_id', ['label' => 'Nome do Usuário', 'options' => $users, 'class' => 'form-control']);
                    echo $this->Form->control('comment', ['label' => 'Comentário', 'class' => 'form-control']);
                ?>
            </fieldset>
            <center><?= $this->Form->button(__('Submit'), ['class' => 'btn btn-primary form-btn']) ?></center>
            <?= $this->Form->end() ?>
        </div>
    </div>
</div>