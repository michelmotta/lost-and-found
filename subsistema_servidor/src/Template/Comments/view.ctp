<?php
/**
 * @var \App\View\AppView $this
 * @var \App\Model\Entity\Comment $comment
 */
?>
<div class="tile">
    <div class="tile-body">
        <div class="comments view large-9 medium-8 columns content">
            <h3><?= h($comment->id) ?></h3>
            <table class="table table-hover">
                <tr>
                    <th scope="row"><?= __('Código do Objeto') ?></th>
                    <td><?= $comment->has('object') ? $this->Html->link($comment->object->title, ['controller' => 'Objects', 'action' => 'view', $comment->object->id]) : '' ?></td>
                </tr>
                <tr>
                    <th scope="row"><?= __('Id') ?></th>
                    <td><?= $this->Number->format($comment->id) ?></td>
                </tr>
                <tr>
                    <th scope="row"><?= __('Criado') ?></th>
                    <td><?= h($comment->created) ?></td>
                </tr>
                <tr>
                    <th scope="row"><?= __('Modificado') ?></th>
                    <td><?= h($comment->modified) ?></td>
                </tr>
            </table>
            <div class="row">
                <div class="col-md-12">
                    <h4><?= __('Comentário') ?></h4>
                    <?= $this->Text->autoParagraph(h($comment->comment)); ?>
                </div>
            </div>
        </div>
    </div>
</div>