<div class="login-box">
    <?= $this->Form->create('', ['class' => 'login-form']) ?>
    <h3 class="login-head"><i class="fa fa-lg fa-fw fa-user"></i><?= __('LOGIN'); ?></h3>
    <div class="form-group">
        <label class="control-label"><?= __('E-MAIL'); ?></label>
        <?= $this->Form->control('email', ['label' => false, 'class' => 'form-control', 'placeholder'=> 'Digite seu e-mail']) ?>
    </div>
    <div class="form-group">
        <label class="control-label"><?= __('SENHA'); ?></label>
        <?= $this->Form->control('password', ['label' => false, 'class' => 'form-control', 'placeholder'=> 'Digite sua senha']) ?>
    </div>
    <div class="form-group btn-container">
        <?= $this->Form->button(__('<i class="fa fa-sign-in fa-lg fa-fw"></i> LOGIN'), ['escape' => false, 'class' => 'btn btn-primary btn-block']) ?>
    </div>
    <?= $this->Form->end() ?>
</div>
