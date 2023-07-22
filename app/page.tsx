'use client'

import { Loading } from "@nextui-org/react"
import { useRouter } from "next/navigation"

export default function Home() {
  const router = useRouter()
  router.push("/dashboard")
  return (
    <div className="flex w-full h-screen justify-center items-center">
      <Loading color="primary" size="xl"/>
    </div>
  )
}
