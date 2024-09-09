<template>
    <div class="chat-window" v-draggable>
        <div class="chat-window-header">
            <div class="chat-window-header-title">
                <span>高考小智聊天栏</span>
            </div>
            <div class="chat-window-header-close">
                <span @click="toggleChat">X</span>
            </div>
        </div>
        <div class="chat-window-body">
            <!-- 历史消息渲染 -->
            <div v-for="message in HistoryMessages" :key="message.id" :class="getMessageClass(message)"
                v-html="message.message">
            </div>
            <!-- 机器人回复消息 -->
            <div class="bot" :class="chat - window - body - message - bot" v-html="renderedMarkdown"></div>
        </div>
        <div class="chat-window-footer">
            <input type="text" class="chat-window-footer-input" v-model="usermessage" placeholder="请输入您的问题...">
            <button class="chat-window-footer-send" @click="sendMessage">发送</button>
        </div>
    </div>
</template>

<script setup>
import { ref, computed } from 'vue';
import ai from '@/api/ai';
import { marked } from 'marked';

const usermessage = ref('');
let Params = '';
let eventSource = null;
let isStreamComplete = false;
let botmessage = ref('');

// 历史消息列表
const HistoryMessages = ref([
    { id: 1, message: '你好！', type: 'bot' },
    { id: 2, message: '请问高考的注意事项有哪些？', type: 'user' },
    { id: 3, message: '高考时要注意保持良好的心态。', type: 'bot' }

]);
// 获取消息的样式
const getMessageClass = (message) => {
    return {
        'chat-window-body-message-bot': message.type === 'bot',
        'chat-window-body-message-user': message.type === 'user'
    };
};

// 关闭ChatWindow事件
const emit = defineEmits(['closeChatWindow']);
const toggleChat = () => {
    emit('closeChatWindow');
};
// 发送消息
const sendMessage = () => {

    if (usermessage.value.trim() !== '') {
        // 请求后端
        Params = usermessage.value;
        receiveStreamData();
        // 将用户消息添加到历史消息列表
        HistoryMessages.value.push({
            id: Date.now(),
            message: usermessage.value,
            type: 'user'
        });
        usermessage.value = ''; // 清空输入框
    }
};
// 测试流式输出函数
// 接受流数据 Params为请求参数(问题)
const receiveStreamData = () => {
    if (eventSource) {
        eventSource.close();
    }
    // 重置流数据状态
    isStreamComplete = false;
    eventSource = ai.fetchStreamData(Params, (data) => {
        console.log(data);
        botmessage.value = botmessage.value + data;
    }, (error) => {
        console.log(error);
        isStreamComplete = true;

        if (botmessage.value === '') {
            botmessage.value = '对不起，我不明白您的问题，请换个问题试试。';
            HistoryMessages.value.push({
                id: Date.now(),
                message: marked(botmessage.value),
                type: 'bot'
            });
            botmessage.value = '';
        } else {
            HistoryMessages.value.push({
                id: Date.now(),
                message: marked(botmessage.value),
                type: 'bot'
            });
            botmessage.value = '';
        }
    });
};
// 将 Markdown 渲染为 HTML
const renderedMarkdown = computed(() => {
    return marked(botmessage.value);
});

const vDraggable = {
  mounted(el) {
    el.style.position = 'absolute';
    el.style.cursor = 'move';

    el.onmousedown = function (e) {
      const disX = e.clientX - el.offsetLeft;
      const disY = e.clientY - el.offsetTop;

      document.onmousemove = function (e) {
        el.style.left = e.clientX - disX + 'px';
        el.style.top = e.clientY - disY + 'px';
      };

      document.onmouseup = function () {
        document.onmousemove = null;
        document.onmouseup = null;
      };
    };
  },
};

defineExpose({ vDraggable });
</script>

<style scoped>
.chat-window {
    position: fixed;
    bottom: 30px;
    right: 0px;
    width: 400px;
    height: 400px;
    background-color: white;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.chat-window-enter-active,
.chat-window-leave-active {
    transition: transform 0.3s ease-in-out;
}

.chat-window-enter-from,
.chat-window-leave-to {
    transform: translateX(100%);
}

.chat-window-enter-to,
.chat-window-leave-from {
    transform: translateX(0);
}

.chat-window-header {
    background-color: #f0f0f0;
    padding: 5px;
    border-bottom: 1px solid #ccc;
    border-top-left-radius: 5px;
    border-top-right-radius: 5px;
    display: flex;
    justify-content: space-between;
}

.chat-window-header-title {
    font-weight: bold;
}

.chat-window-header-close {
    cursor: pointer;
}

.chat-window-body {
    padding: 15px;
    height: 300px;
    overflow-y: auto;
    display: flex;
    flex-direction: column;
}

.chat-window-body-message-bot {
    background-color: #f0f0f0;
    padding: 10px;
    display: inline-block;
    /* 使背景宽度根据内容调整 */
    max-width: 80%;
    /* 限制最大宽度 */
    border-radius: 5px;
    margin-bottom: 10px;
}

.chat-window-body-message-user {
    background-color: #42b983;
    color: white;
    padding: 10px;
    display: inline-block;
    /* 使背景宽度根据内容调整 */
    max-width: 80%;
    /* 限制最大宽度 */
    border-radius: 5px;
    margin-bottom: 10px;
    align-self: flex-end;
    /* 使用户消息靠右 */

}

.chat-window-footer {
    padding: 10px;
    display: flex;
    position: relative;
    bottom: 10px;
}

.chat-window-footer-input {
    flex: 1;
    padding: 5px;
    width: 60%;
    border: 1px solid #ccc;
    border-radius: 5px;
}

.chat-window-footer-send {
    padding: 5px 10px;
    background-color: #42b983;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}
</style>
