<template>
  <div class="DispoInfo">
    <h5>Disponibilité:</h5>
    <div>
    <div v-for="item in dispo" :key="item">
      <p>{{ item[0]}} : {{ item[1]}} (nb disponibles)</p>
      
    </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "DispoInfo",
  props: {
    dispo: {
      type: Object,
      required: true,
    },
  },
  created() {
    console.log(this.dispo);
  },
};
</script>
<style scoped>
.DispoInfo{
  display: flex;
  justify-content: flex-start;
}
h5{
  margin: 10px 50px 10px 30px;
}

@media only screen and (max-width: 576px){
  .DispoInfo{
    flex-direction: column;
  }
}

</style>
