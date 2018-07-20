
<div class="row" style="margin-top: 30px;">
    <div class="col-md-6">
        <div class="tile tile-color">
            <div class="tile-body">
                <div class="single-thumb">
                    <center><img src="<?= $object->image; ?>" class="img-fluid"/></center>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-6">
    <div class="tile tile-color">
        <div class="tile-body">
                <div class="single-thumb">
                    <center><h1 style="color:#FFFFFF;"><?= $object->title; ?></h1></center>
                </div>
                <hr>
                <div class="object-solved">
                    <center><p class="p1">Situação do Objeto</p></center>
                    <center><p class="p2"><?= $object->solved; ?></p></center>
                </div>
                <hr>
                <div class="object-type">
                    <center><p class="p1">Classificação do Objeto</p></center>
                    <center><p class="p2"><?= $object->type; ?></p></center>
                </div>
                <hr>
                <div class="object-date">
                    <center><p class="p1">Data do Ocorrido</p></center>
                    <center><p class="p2"><?= $object->date; ?></p></center>
                </div>
                <hr>
                <div class="object-desc">
                    <center><p class="p1">Descrição do Ocorrido</p></center>
                    <center><p class="p2"><?= $object->description; ?></p></center>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="tile tile-color">
    <div class="tile-body">
        <center><h2 style="color:#FFFFFF">Comentários</h2></center>
        <?php if($this->request->session()->read('Auth.User.id')) {?>
            <div class="comments form large-9 medium-8 columns content">
                <?= $this->Form->create(null, ['url' => ['controller' => 'Comments', 'action' => 'comment']]) ?>
                <fieldset>
                    <legend style="color:#FFFFFF"><?= __('Comentar') ?></legend>
                    <?php
                        echo $this->Form->control('object_id', ['type' => 'hidden', 'value' => $object->id]);
                        echo $this->Form->control('object_user_id', ['type' => 'hidden', 'value' => $object->user_id]);
                        echo $this->Form->control('user_id', ['type' => 'hidden', 'value' => $this->request->session()->read('Auth.User.id')]);
                        echo $this->Form->control('comment', ['type' => 'textarea', 'label' => false, 'class' => 'form-control']);
                    ?>
                </fieldset>
                <center><?= $this->Form->button(__('Salvar'), ['class' => 'btn btn-primary form-btn']) ?></center>
                <?= $this->Form->end() ?>
            </div>
        <?php } ?>
        <div class="related" style="margin-top: 30px;">
            <?php if (!empty($object->comments)): ?>
            <table class="table table-hover" style="color: #FFFFFF;">
                <tr>
                    <th scope="col"><?= __('Comentário') ?></th>
                    <th scope="col"><?= __('Publicado Por') ?></th>
                    <th scope="col"><?= __('Publicado Em') ?></th>
                </tr>
                <?php foreach ($object->comments as $comments): ?>
                <tr>
                    <td><?= h($comments->comment) ?></td>
                    <td><?= $this->Comments->userCommentName($comments->user_id) ?></td>
                    <td><?= h($comments->created) ?></td>
                </tr>
                <?php endforeach; ?>
            </table>
            <?php endif; ?>
        </div>

    </div>
</div>
