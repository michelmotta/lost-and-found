<?php
/**
 * @var \App\View\AppView $this
 * @var \App\Model\Entity\Comment[]|\Cake\Collection\CollectionInterface $comments
 */
?>
<div class="tile">
    <div class="tile-body">
        <div class="comments index large-9 medium-8 columns content">
            <h3><?= __('Comentários') ?></h3>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th scope="col"><?= $this->Paginator->sort('id', __('Código')) ?></th>
                        <th scope="col"><?= $this->Paginator->sort('object_id', __('Nome do Objeto')) ?></th>
                        <th scope="col"><?= $this->Paginator->sort('user_id', __('Publicado Por')) ?></th>
                        <th scope="col"><?= $this->Paginator->sort('created', __('Publicado Em')) ?></th>
                        <th scope="col" class="actions"><?= __('Ações') ?></th>
                    </tr>
                </thead>
                <tbody>
                    <?php foreach ($comments as $comment): ?>
                    <tr>
                        <td><?= $this->Number->format($comment->id) ?></td>
                        <td><?= $comment->has('object') ? $this->Html->link($comment->object->title, ['controller' => 'Objects', 'action' => 'view', $comment->object->id]) : '' ?></td>
                        <td><?= $comment->has('object') ? $this->Html->link($comment->user->username, ['controller' => 'Users', 'action' => 'view', $comment->user->id]) : '' ?></td>
                        <td><?= h($comment->created) ?></td>
                        <td class="actions">
                            <?= $this->Html->link(__('Ver'), ['action' => 'view', $comment->id]) ?>
                            <?= $this->Html->link(__('Editar'), ['action' => 'edit', $comment->id]) ?>
                            <?= $this->Form->postLink(__('Deletar'), ['action' => 'delete', $comment->id], ['confirm' => __('Are you sure you want to delete # {0}?', $comment->id)]) ?>
                        </td>
                    </tr>
                    <?php endforeach; ?>
                </tbody>
            </table>
            <div class="paginator">
            <ul class="pagination">
                    <?= $this->Paginator->first('<< ' . __('primeiro')) ?>
                    <?= $this->Paginator->prev('< ' . __('anterior')) ?>
                    <?= $this->Paginator->numbers() ?>
                    <?= $this->Paginator->next(__('próximo') . ' >') ?>
                    <?= $this->Paginator->last(__('último') . ' >>') ?>
                </ul>
                <p><?= $this->Paginator->counter(['format' => __('Página {{page}} de {{pages}}, mostrando {{current}} registro(s) de {{count}} total')]) ?></p>
            </div>
        </div>
    </div>
</div>