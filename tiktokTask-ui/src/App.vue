<template>
  <div id="app">
    <router-view />
    <theme-picker />
  </div>
</template>

<script>
import ThemePicker from "@/components/ThemePicker";

export default {
  name: "App",
  components: { ThemePicker },
  metaInfo() {
    return {
      title: this.$store.state.settings.dynamicTitle && this.$store.state.settings.title,
      titleTemplate: title => {
        return title ? `${title} - ${process.env.VUE_APP_TITLE}` : process.env.VUE_APP_TITLE
      }
    }
  }
};
</script>
<style scoped>
#app .theme-picker {
  display: none;
}
</style>
<style>

.tag-color1 {
  color: #00d1ff;
}

.tag-color2 {
  color: #0066ff;
}

.tag-color3 {
  color: #9e00ff;
}

.tag-color4 {
  color: #ff004d;
}

.tag-color5 {
  color: #ff6b00;
}

.tag-color6 {
  color: #f4de10;
}
</style>
