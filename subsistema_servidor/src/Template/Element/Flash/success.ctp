<?php
if (!isset($params['escape']) || $params['escape'] !== false) {
    $message = h($message);
}
?>
<script type="text/javascript">
	$( document ).ready(function() {
		  $.notify({
        		message: "<?= $message ?>",
        		icon: 'fa fa-check'
      	 },{
      		   type: "success",
             delay: 10000,
      		   animate: {
    				enter: 'animated bounceInDown',
    				exit: 'animated bounceOutUp'
			   }
      	 });
	    });
</script>

