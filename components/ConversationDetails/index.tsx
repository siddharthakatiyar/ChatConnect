import { KeyboardEvent, useContext, useEffect, useState } from "react";
import { ConversationContext } from "../../context/ConversationContext";
import Avatar from "../Avatar";
import MessageBalloon from "../MessageBalloon";
import Send from "../MessageHandler/send";
import { initiateSocket, getSocket } from "../../utils/websocket";

export default function ConversationDetails() {
  const { conversation, message, setMessage } = useContext(ConversationContext);
  const { username, messageHistory } = conversation;
  const [messageSend, setMessageSend] = useState("");

  useEffect(() => {

    initiateSocket({ username });
    setMessage(messageHistory);
    const socket = getSocket();

    socket.on('receive_message', (data: any) => {
      setMessage([...messageHistory, { me: false, message: data.message, created_at: data.created_at }]);
    });

  }, [conversation]);


  function changeHandler(evt: KeyboardEvent<HTMLInputElement>) {
    const { key } = evt;

    if (key === "Enter") {
      const socket = getSocket();
      setMessage([...message, { me: true, message: messageSend, created_at: new Date().toISOString() }]);
      socket.emit('send_message', {
        receiver_username: username,
        message: messageSend,
        created_at: new Date().toISOString(),
      });
      setMessageSend("");
    }
  }

  return (
    <div className="flex flex-col w-full">
      <div className="flex justify-between w-full px-4">
        <div className="flex justify-between bg-[#fbfaf5] w-full h-14 border-b border-[rgba(120,40,200,1)]">
          <div className="flex items-center gap-4 h-full">
            <Avatar width="w-10" height="h-10" />
            <h1 className="text-black font-normal">{username}</h1>
          </div>
        </div>
      </div>
      <div className="flex flex-col w-full h-full px-24 py-6 overflow-y-auto" style={{ backgroundImage: "url('/chatbackground.png')", backgroundSize: "cover", backgroundRepeat: "no-repeat", backgroundPosition: "center", backgroundColor: "#fbfaf5" }}>
        {
          message.map((messageConversation, index) => {
            const { me, message, created_at } = messageConversation;

            return (
              <MessageBalloon key={index} me={me} message={message} created_at={created_at} />
            )
          })
        }
      </div>
      <footer className="flex items-center bg-[#fbfaf5] w-full h-16 py-3 text-[#8696a0]">
        <div className="flex w-[97.5%] h-12 ml-3">
          <input type={"text"} className="bg-[#e0e0e0] rounded-lg w-full px-3 py-3 text-white" placeholder="Type a message" onKeyDown={(evt) => changeHandler(evt)} onChange={(evt) => setMessageSend(evt.target.value)} value={messageSend} />
        </div>
      </footer>
    </div>
  )
}