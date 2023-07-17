import Avatar from "../Avatar";
import ConversationList from "../ConversationList";
import { useState } from "react";
import Recent from "../MessageHandler/recent";
import LogOut from "../Auth/LogOut";

export default function SideBar() {
  const conversationsList = Recent();
  const [ search, setSearch ] = useState("");
  const filteredConversationsList = conversationsList.filter((conversation) => {
    return conversation.username.toLowerCase().includes(search.toLowerCase());
  });

  return (
    <div className="flex flex-col w-[480px] h-full bg-[#d9d9d9]" style={{borderRight: "1px solid rgba(134,150,160,0.15)"}}>
      <div className="flex items-center justify-between w-full px-4">
        <div className="flex bg-[#d9d9d9] w-full h-14 py-3 items-center">
          <div className="flex cursor-pointer">
            <Avatar width="w-10" height="w-10"/>
          </div>
        </div>
        <div className="flex gap-2">
          
          <div className="flex cursor-pointer w-10 h-10 items-center justify-center"
            onClick={ () => LogOut() }
          >
            <img src="/new.png" alt="new chat" className="w-7 h-7" />
          </div>
          <div className="flex cursor-pointer w-10 h-10 items-center justify-center"
            onClick={ () => LogOut() }
          >
            <img src="/logout.png" alt="logout" className="w-6 h-6" />
          </div>
        </div>
      </div>
      <div className="flex bg-[#d9d9d9] w-full h-max px-3 py-2">
        <div className="relative w-[95%] h-max">
          <div className="absolute text-[#AEBAC1] h-full w-9">
            <svg viewBox="0 0 24 24" width="24" height="24" className="left-[50%] right-[50%] ml-auto mr-auto h-full">
              <path fill="currentColor" d="M15.009 13.805h-.636l-.22-.219a5.184 5.184 0 0 0 1.256-3.386 5.207 5.207 0 1 0-5.207 5.208 5.183 5.183 0 0 0 3.385-1.255l.221.22v.635l4.004 3.999 1.194-1.195-3.997-4.007zm-4.808 0a3.605 3.605 0 1 1 0-7.21 3.605 3.605 0 0 1 0 7.21z">
              </path>
            </svg>
          </div>
          <div className="">
            <input className="w-[103%] h-9 rounded-lg bg-[#e0e0e0] text-white text-sm px-10" placeholder="Search recent chats..." value={search} onChange={e => setSearch(e.target.value)} />
          </div>
        </div>
      </div>
      <div className="flex flex-col w-full overflow-y-scroll" id="conversation">
        {
          filteredConversationsList.map( (conversation, index) => {
            return (
              <ConversationList key={index} isFirstConversation={index == 0} data={conversation} />
            )
          })
        }
      </div>
    </div>
  )
}