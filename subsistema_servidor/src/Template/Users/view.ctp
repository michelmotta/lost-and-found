<?php
/**
 * @var \App\View\AppView $this
 * @var \App\Model\Entity\User $user
 */
?>
<div class="tile">
    <div class="tile-body">
        <div class="users view large-9 medium-8 columns content">
            <h3><?= h($user->id) ?></h3>
            <table class="table table-hover">
                <tr>
                    <th scope="row"><?= __('Nome de Usuário') ?></th>
                    <td><?= h($user->username) ?></td>
                </tr>
                <tr>
                    <th scope="row"><?= __('E-mail') ?></th>
                    <td><?= h($user->email) ?></td>
                </tr>
                <tr>
                    <th scope="row"><?= __('Id') ?></th>
                    <td><?= $this->Number->format($user->id) ?></td>
                </tr>
                <tr>
                    <th scope="row"><?= __('Criado') ?></th>
                    <td><?= h($user->created) ?></td>
                </tr>
                <tr>
                    <th scope="row"><?= __('Modificado') ?></th>
                    <td><?= h($user->modified) ?></td>
                </tr>
            </table>
            <div class="related">
                <h4><?= __('Objetos Cadastrados') ?></h4>
                <?php if (!empty($user->objects)): ?>
                <table class="table table-sm">
                    <tr>
                        <th scope="col"><?= __('Codigo') ?></th>
                        <th scope="col"><?= __('Título') ?></th>
                        <th scope="col"><?= __('Situação do Objeto') ?></th>
                        <th scope="col"><?= __('Classificação') ?></th>
                        <th scope="col"><?= __('Data do Ocorrido') ?></th>
                        <th scope="col"><?= __('Imagem') ?></th>
                        <th scope="col"><?= __('Descrição do Ocorrido') ?></th>
                        <th scope="col"><?= __('Criado') ?></th>
                        <th scope="col"><?= __('Modificado') ?></th>
                        <th scope="col" class="actions"><?= __('Actions') ?></th>
                    </tr>
                    <?php foreach ($user->objects as $objects): ?>
                    <tr>
                        <td><?= h($objects->id) ?></td>
                        <td><?= h($objects->title) ?></td>
                        <td><?= h($objects->solved) ?></td>
                        <td><?= h($objects->type) ?></td>
                        <td><?= h($objects->date) ?></td>
                        <td><?= h($objects->image) ?></td>
                        <td><?= h($objects->description) ?></td>
                        <td><?= h($objects->created) ?></td>
                        <td><?= h($objects->modified) ?></td>
                        <td class="actions">
                            <?= $this->Html->link(__('Ver'), ['controller' => 'Objects', 'action' => 'view', $objects->id]) ?>
                            <?= $this->Html->link(__('Editar'), ['controller' => 'Objects', 'action' => 'edit', $objects->id]) ?>
                            <?= $this->Form->postLink(__('Deletar'), ['controller' => 'Objects', 'action' => 'delete', $objects->id], ['confirm' => __('Are you sure you want to delete # {0}?', $objects->id)]) ?>
                        </td>
                    </tr>
                    <?php endforeach; ?>
                </table>
                <?php endif; ?>
            </div>
        </div>
    </div>
</div>