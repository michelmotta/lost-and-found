<?php
/**
 * @var \App\View\AppView $this
 * @var \App\Model\Entity\User[]|\Cake\Collection\CollectionInterface $users
 */
?>
<div class="tile">
    <div class="tile-body">
        <div class="users index large-9 medium-8 columns content">
            <h3><?= __('Usuários') ?></h3>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th scope="col"><?= $this->Paginator->sort('id', __('Código')) ?></th>
                        <th scope="col"><?= $this->Paginator->sort('username', __('Nome de Usuário')) ?></th>
                        <th scope="col"><?= $this->Paginator->sort('email', __('E-mail')) ?></th>
                        <th scope="col"><?= $this->Paginator->sort('created', __('Criado')) ?></th>
                        <th scope="col"><?= $this->Paginator->sort('modified', __('Modificado')) ?></th>
                        <th scope="col" class="actions"><?= __('Ações') ?></th>
                    </tr>
                </thead>
                <tbody>
                    <?php foreach ($users as $user): ?>
                    <tr>
                        <td><?= $this->Number->format($user->id) ?></td>
                        <td><?= h($user->username) ?></td>
                        <td><?= h($user->email) ?></td>
                        <td><?= h($user->created) ?></td>
                        <td><?= h($user->modified) ?></td>
                        <td class="actions">
                            <?= $this->Html->link(__('Ver'), ['action' => 'view', $user->id]) ?>
                            <?= $this->Html->link(__('Editar'), ['action' => 'edit', $user->id]) ?>
                            <?= $this->Form->postLink(__('Deletar'), ['action' => 'delete', $user->id], ['confirm' => __('Are you sure you want to delete # {0}?', $user->id)]) ?>
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