<script lang="ts" allow-js>
import { defineComponent } from 'vue';
import { userStore } from '@/stores/user';
import { mapState } from 'pinia';
export default defineComponent({
    computed: {
        ...mapState(userStore, {
            loggedCustomer: 'loggedCustomer'
        }),
        wellcomeCustomer() {
            if (this.loggedCustomer != null) {
                return 'Xin chào ' + this.loggedCustomer + '! Chúc một ngày tốt lành 😘'
            } else {
                return 'Chúc một ngày tốt lành 😘'
            }
        }
    },
    mounted() {
        var dt = new Date();
        var element = document.getElementById('date-time');
        if (element) {
            element.innerHTML = dt.toString();
        }
        setInterval(() => {
            this.updateTime();
        }, 1000);
    },
    methods: {
        updateTime() {
            var now = new Date();
            var hours = now.getHours();
            var minutes: string | number = now.getMinutes();
            var seconds: string | number = now.getSeconds();
            var ampm = hours >= 12 ? 'PM' : 'AM';
            hours = hours % 12;
            hours = hours ? hours : 12;
            minutes = minutes < 10 ? '0' + minutes : minutes;
            seconds = seconds < 10 ? '0' + seconds : seconds;
            var timeString = hours + ':' + minutes + ':' + seconds + ' ' + ampm;
            var currentTime = document.getElementById('current-time')
            if(currentTime) {
                currentTime.innerHTML = timeString.toString();
            }
        }
    }
})
</script>

<template>
    <div class="w-[900px]">
        <footer class="flex flex-row justify-between">
            <h4>{{ wellcomeCustomer }}</h4>
            <p>
                <span id="current-time"></span>
            </p>
        </footer>
    </div>
</template>

